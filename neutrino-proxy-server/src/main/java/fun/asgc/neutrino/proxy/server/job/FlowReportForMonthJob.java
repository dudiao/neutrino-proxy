/**
 * Copyright (c) 2022 aoshiguchen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fun.asgc.neutrino.proxy.server.job;

import fun.asgc.neutrino.core.annotation.Autowired;
import fun.asgc.neutrino.core.annotation.Component;
import fun.asgc.neutrino.core.annotation.NonIntercept;
import fun.asgc.neutrino.core.quartz.IJobHandler;
import fun.asgc.neutrino.core.quartz.annotation.JobHandler;
import fun.asgc.neutrino.core.util.CollectionUtil;
import fun.asgc.neutrino.core.util.DateUtil;
import fun.asgc.neutrino.proxy.server.dal.*;
import fun.asgc.neutrino.proxy.server.dal.entity.FlowReportDayDO;
import fun.asgc.neutrino.proxy.server.dal.entity.FlowReportMonthDO;
import fun.asgc.neutrino.proxy.server.service.FlowReportService;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author: aoshiguchen
 * @date: 2022/10/28
 */
@Slf4j
@NonIntercept
@Component
@JobHandler(name = "FlowReportForMonthJob", cron = "0 10 0 1 * ?", param = "")
public class FlowReportForMonthJob implements IJobHandler {
    @Autowired
    private FlowReportService flowReportService;
    @Autowired
    private LicenseMapper licenseMapper;
    @Autowired
    private FlowReportMinuteMapper flowReportMinuteMapper;
    @Autowired
    private FlowReportHourMapper flowReportHourMapper;
    @Autowired
    private FlowReportDayMapper flowReportDayMapper;
    @Autowired
    private FlowReportMonthMapper flowReportMonthMapper;

    @Override
    public void execute(String param) throws Exception {
        Date now = new Date();
        String dateStr = DateUtil.format(DateUtil.addDate(now, Calendar.MONTH, -1), "yyyy-MM");
        Date date = DateUtil.parse(dateStr, "yyyy-MM");
        Date startDayDate = DateUtil.getDayBegin(date);
        Date endEndDate = DateUtil.getDayEnd(date);

        // 删除原来的记录
        flowReportDayMapper.deleteByDateStr(dateStr);

        // 查询上个月的天级别统计数据
        List<FlowReportDayDO> flowReportDayList = flowReportDayMapper.findListByDateRange(startDayDate, endEndDate);
        if (CollectionUtil.isEmpty(flowReportDayList)) {
            return;
        }

        // 汇总前一个天的天级别统计数据
        Map<Integer, FlowReportMonthDO> map = new HashMap<>();
        for (FlowReportDayDO item : flowReportDayList) {
            FlowReportMonthDO report = map.get(item.getLicenseId());
            if (null == report) {
                report = new FlowReportMonthDO();
                map.put(item.getLicenseId(), report);
            }
            Long writeBytes = report.getWriteBytes() == null ? 0 : report.getWriteBytes();
            Long readBytes = report.getReadBytes() == null ? 0 : report.getReadBytes();

            report.setUserId(item.getUserId());
            report.setLicenseId(item.getLicenseId());
            report.setWriteBytes(writeBytes + item.getWriteBytes());
            report.setReadBytes(readBytes + item.getReadBytes());
            report.setDate(date);
            report.setDateStr(dateStr);
            report.setCreateTime(now);
        }

        for (FlowReportMonthDO item : map.values()) {
            flowReportMonthMapper.add(item);
        }
    }

}