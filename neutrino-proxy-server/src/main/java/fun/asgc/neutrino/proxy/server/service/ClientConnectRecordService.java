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
package fun.asgc.neutrino.proxy.server.service;

import fun.asgc.neutrino.core.annotation.Autowired;
import fun.asgc.neutrino.core.annotation.Component;
import fun.asgc.neutrino.core.annotation.NonIntercept;
import fun.asgc.neutrino.core.db.page.Page;
import fun.asgc.neutrino.core.db.page.PageQuery;
import fun.asgc.neutrino.core.util.CollectionUtil;
import fun.asgc.neutrino.proxy.server.base.rest.SystemContextHolder;
import fun.asgc.neutrino.proxy.server.controller.req.ClientConnectRecordListReq;
import fun.asgc.neutrino.proxy.server.controller.res.ClientConnectRecordListRes;
import fun.asgc.neutrino.proxy.server.dal.ClientConnectRecordMapper;
import fun.asgc.neutrino.proxy.server.dal.LicenseMapper;
import fun.asgc.neutrino.proxy.server.dal.UserMapper;
import fun.asgc.neutrino.proxy.server.dal.entity.ClientConnectRecordDO;
import fun.asgc.neutrino.proxy.server.dal.entity.LicenseDO;
import fun.asgc.neutrino.proxy.server.dal.entity.UserDO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: aoshiguchen
 * @date: 2022/11/23
 */
@Slf4j
@NonIntercept
@Component
public class ClientConnectRecordService {
    @Autowired
    private ClientConnectRecordMapper clientConnectRecordMapper;
    @Autowired
    private LicenseMapper licenseMapper;
    @Autowired
    private UserMapper userMapper;

    public void add(ClientConnectRecordDO clientConnectRecordDO) {
        clientConnectRecordMapper.add(clientConnectRecordDO);
    }

    public Page<ClientConnectRecordListRes> page(PageQuery pageQuery, ClientConnectRecordListReq req) {
        Page<ClientConnectRecordListRes> page = Page.create(pageQuery);
        clientConnectRecordMapper.page(page, req);
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return page;
        }
        Set<Integer> licenseIds = page.getRecords().stream().map(ClientConnectRecordListRes::getLicenseId).collect(Collectors.toSet());
        if (CollectionUtil.isEmpty(licenseIds)) {
            return page;
        }
        List<LicenseDO> licenseList = licenseMapper.findByIds(licenseIds);
        if (CollectionUtil.isEmpty(licenseList)) {
            return page;
        }
        Set<Integer> userIds = licenseList.stream().map(LicenseDO::getUserId).collect(Collectors.toSet());
        List<UserDO> userList = userMapper.findByIds(userIds);
        Map<Integer, LicenseDO> licenseMap = licenseList.stream().collect(Collectors.toMap(LicenseDO::getId, Function.identity()));
        Map<Integer, UserDO> userMap = userList.stream().collect(Collectors.toMap(UserDO::getId, Function.identity()));
        boolean isAdmin = SystemContextHolder.isAdmin();
        page.getRecords().forEach(item -> {
            LicenseDO license = licenseMap.get(item.getLicenseId());
            if (null == license) {
                return;
            }
            item.setLicenseName(license.getName());
            item.setUserId(license.getUserId());
            UserDO user = userMap.get(license.getUserId());
            if (null == user) {
                return;
            }
            item.setUserName(user.getName());
            if (!isAdmin) {
                // msg可能带有license等敏感信息，若登录者为游客，则不展示
                item.setMsg("******");
            }
        });
        return page;
    }
}