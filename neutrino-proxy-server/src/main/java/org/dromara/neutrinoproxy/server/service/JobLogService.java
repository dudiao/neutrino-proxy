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
package org.dromara.neutrinoproxy.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.solon.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.solon.annotation.Db;
import org.dromara.neutrinoproxy.server.base.page.PageInfo;
import org.dromara.neutrinoproxy.server.base.page.PageQuery;
import org.dromara.neutrinoproxy.server.controller.req.log.JobLogListReq;
import org.dromara.neutrinoproxy.server.controller.res.log.JobLogListRes;
import org.dromara.neutrinoproxy.server.dal.JobLogMapper;
import org.dromara.neutrinoproxy.server.dal.entity.JobLogDO;
import org.dromara.solonplugins.job.IJobCallback;
import org.dromara.solonplugins.job.JobInfo;
import org.noear.solon.annotation.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author: aoshiguchen
 * @date: 2022/9/4
 */
@Slf4j
@Component
public class JobLogService implements IJobCallback {
	@Db
	private JobLogMapper jobLogMapper;

	@Override
	public void executeLog(JobInfo jobInfo, String param, Throwable throwable) {
		Integer code = 0;
		String msg = "";
		if (null == throwable) {
			msg = "execute success";
			log.debug("job[id={},name={}]execute success", jobInfo.getId(), jobInfo.getName());
		} else {
			log.error("job[id={},name={}]execute error", jobInfo.getId(), jobInfo.getName(), throwable);
			msg = "execute error:\r\n" + ExceptionUtils.getStackTrace(throwable);
			code = -1;
		}
		jobLogMapper.insert(new JobLogDO()
				.setJobId(Integer.valueOf(jobInfo.getId()))
				.setHandler(jobInfo.getName())
				.setParam(param)
				.setCode(code)
				.setMsg(msg)
				.setAlarmStatus(0)
				.setCreateTime(new Date())
		);
	}

	public PageInfo<JobLogListRes> page(PageQuery pageQuery, JobLogListReq req) {
        Page<JobLogDO> page = jobLogMapper.selectPage(new Page<>(pageQuery.getCurrent(), pageQuery.getSize()), new LambdaQueryWrapper<JobLogDO>()
            .eq(null != req.getJobId(), JobLogDO::getJobId, req.getJobId())
            .orderByDesc(JobLogDO::getId)
        );
        List<JobLogListRes> respList = page.getRecords().stream().map(JobLogDO::toRes).collect(Collectors.toList());
		return PageInfo.of(respList, page);
	}

}
