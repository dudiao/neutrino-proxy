package org.dromara.neutrinoproxy.server.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Sets;
import ma.glasnost.orika.MapperFacade;
import org.apache.ibatis.solon.annotation.Db;
import org.dromara.neutrinoproxy.server.base.db.DBInitialize;
import org.dromara.neutrinoproxy.server.base.page.PageInfo;
import org.dromara.neutrinoproxy.server.base.page.PageQuery;
import org.dromara.neutrinoproxy.server.base.rest.ServiceException;
import org.dromara.neutrinoproxy.server.base.rest.SystemContextHolder;
import org.dromara.neutrinoproxy.server.constant.EnableStatusEnum;
import org.dromara.neutrinoproxy.server.constant.ExceptionConstant;
import org.dromara.neutrinoproxy.server.constant.OnlineStatusEnum;
import org.dromara.neutrinoproxy.server.controller.req.proxy.LicenseCreateReq;
import org.dromara.neutrinoproxy.server.controller.req.proxy.LicenseListReq;
import org.dromara.neutrinoproxy.server.controller.req.proxy.LicenseUpdateEnableStatusReq;
import org.dromara.neutrinoproxy.server.controller.req.proxy.LicenseUpdateReq;
import org.dromara.neutrinoproxy.server.controller.res.proxy.*;
import org.dromara.neutrinoproxy.server.dal.LicenseMapper;
import org.dromara.neutrinoproxy.server.dal.PortMappingMapper;
import org.dromara.neutrinoproxy.server.dal.UserMapper;
import org.dromara.neutrinoproxy.server.dal.entity.LicenseDO;
import org.dromara.neutrinoproxy.server.dal.entity.PortMappingDO;
import org.dromara.neutrinoproxy.server.dal.entity.UserDO;
import org.dromara.neutrinoproxy.server.util.ParamCheckUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Init;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.bean.LifecycleBean;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * license服务
 *
 * @author: aoshiguchen
 * @date: 2022/8/6
 */
@Component
public class LicenseService implements LifecycleBean {
    @Inject
    private MapperFacade mapperFacade;
    @Db
    private LicenseMapper licenseMapper;
    @Db
    private PortMappingMapper portMappingMapper;
    @Db
    private UserMapper userMapper;
    @Inject
    private VisitorChannelService visitorChannelService;
    @Inject
    private DBInitialize dbInitialize;

    public PageInfo<LicenseListRes> page(PageQuery pageQuery, LicenseListReq req) {
        Page<LicenseListRes> result = PageHelper.startPage(pageQuery.getCurrent(), pageQuery.getSize());
        List<LicenseDO> list = licenseMapper.selectList(new LambdaQueryWrapper<LicenseDO>()
                .eq(req.getUserId() != null, LicenseDO::getUserId, req.getUserId())
                .eq(req.getIsOnline() != null, LicenseDO::getIsOnline, req.getIsOnline())
                .eq(req.getEnable() != null, LicenseDO::getEnable, req.getEnable())
                .orderByAsc(Arrays.asList(LicenseDO::getUserId, LicenseDO::getId))
        );
        List<LicenseListRes> respList = mapperFacade.mapAsList(list, LicenseListRes.class);
        if (CollectionUtils.isEmpty(list)) {
            return PageInfo.of(respList, result.getTotal(), pageQuery.getCurrent(), pageQuery.getSize());
        }
        if (!CollectionUtil.isEmpty(respList)) {
            Set<Integer> userIds = respList.stream().map(LicenseListRes::getUserId).collect(Collectors.toSet());
            List<UserDO> userList = userMapper.findByIds(userIds);
            Map<Integer, UserDO> userMap = userList.stream().collect(Collectors.toMap(UserDO::getId, Function.identity()));
            for (LicenseListRes item : respList) {
                UserDO userDO = userMap.get(item.getUserId());
                if (null != userDO) {
                    item.setUserName(userDO.getName());
                }
                item.setKey(desensitization(item.getUserId(), item.getKey()));
            }
        }
        return PageInfo.of(respList, result.getTotal(), pageQuery.getCurrent(), pageQuery.getSize());
    }

    public List<LicenseListRes> list(LicenseListReq req) {
        List<LicenseDO> list = licenseMapper.selectList(new LambdaQueryWrapper<LicenseDO>()
                .eq(null != req.getEnable(), LicenseDO::getEnable, req.getEnable())
        );
        List<LicenseListRes> licenseList = assembleConvertLicenses(list);
        return licenseList;
    }

    private List<LicenseListRes> assembleConvertLicenses(List<LicenseDO> list) {
        List<LicenseListRes> licenseList = mapperFacade.mapAsList(list, LicenseListRes.class);
        if (!CollectionUtil.isEmpty(licenseList)) {
            Set<Integer> userIds = licenseList.stream().map(LicenseListRes::getUserId).collect(Collectors.toSet());
            List<UserDO> userList = userMapper.findByIds(userIds);
            Map<Integer, UserDO> userMap = userList.stream().collect(Collectors.toMap(UserDO::getId, Function.identity()));
            for (LicenseListRes item : licenseList) {
                UserDO userDO = userMap.get(item.getUserId());
                if (null != userDO) {
                    item.setUserName(userDO.getName());
                }
                item.setKey(desensitization(item.getUserId(), item.getKey()));
            }
        }
        return licenseList;
    }

    /**
     * 创建license
     *
     * @param req
     * @return
     */
    public LicenseCreateRes create(LicenseCreateReq req) {
        LicenseDO licenseDO = licenseMapper.checkRepeat(req.getUserId(), req.getName());
        ParamCheckUtil.checkExpression(null == licenseDO, ExceptionConstant.LICENSE_NAME_CANNOT_REPEAT);

        String key = UUID.randomUUID().toString().replaceAll("-", "");
        Date now = new Date();

        licenseMapper.insert(new LicenseDO()
                .setName(req.getName())
                .setKey(key)
                .setUserId(req.getUserId())
                .setIsOnline(OnlineStatusEnum.OFFLINE.getStatus())
                .setEnable(EnableStatusEnum.ENABLE.getStatus())
                .setCreateTime(now)
                .setUpdateTime(now)
        );
        return new LicenseCreateRes();
    }

    public LicenseUpdateRes update(LicenseUpdateReq req) {
        LicenseDO oldLicenseDO = licenseMapper.findById(req.getId());
        ParamCheckUtil.checkNotNull(oldLicenseDO, ExceptionConstant.LICENSE_NOT_EXIST);

        LicenseDO licenseCheck = licenseMapper.checkRepeat(oldLicenseDO.getUserId(), req.getName(), Sets.newHashSet(oldLicenseDO.getId()));
        ParamCheckUtil.checkMustNull(licenseCheck, ExceptionConstant.LICENSE_NAME_CANNOT_REPEAT);

        licenseMapper.update(req.getId(), req.getName(), new Date());
        return new LicenseUpdateRes();
    }

    public LicenseDetailRes detail(Integer id) {
        LicenseDO licenseDO = licenseMapper.findById(id);
        if (null == licenseDO) {
            return null;
        }
        UserDO userDO = userMapper.findById(licenseDO.getUserId());
        String userName = "";
        if (null != userDO) {
            userName = userDO.getName();
        }
        return new LicenseDetailRes()
                .setId(licenseDO.getId())
                .setName(licenseDO.getName())
                .setKey(desensitization(licenseDO.getUserId(), licenseDO.getKey()))
                .setUserId(licenseDO.getUserId())
                .setUserName(userName)
                .setIsOnline(licenseDO.getIsOnline())
                .setEnable(licenseDO.getEnable())
                .setCreateTime(licenseDO.getCreateTime())
                .setUpdateTime(licenseDO.getUpdateTime())
                ;
    }

    /**
     * 更新license启用状态
     *
     * @param req
     * @return
     */
    public LicenseUpdateEnableStatusRes updateEnableStatus(LicenseUpdateEnableStatusReq req) {
        licenseMapper.updateEnableStatus(req.getId(), req.getEnable(), new Date());
        // 更新VisitorChannel
        visitorChannelService.updateVisitorChannelByLicenseId(req.getId(), req.getEnable());
        return new LicenseUpdateEnableStatusRes();
    }

    /**
     * 删除license
     *
     * @param id
     */
    public void delete(Integer id) {
        List<PortMappingDO> portMappingDOList = portMappingMapper.findListByLicenseId(id);
        if (CollectionUtil.isNotEmpty(portMappingDOList)) {
            throw ServiceException.create(ExceptionConstant.LICENSE_CANNOT_BE_DELETED, portMappingDOList.size());
        }
        licenseMapper.deleteById(id);
        // 更新VisitorChannel
        visitorChannelService.updateVisitorChannelByLicenseId(id, EnableStatusEnum.DISABLE.getStatus());
    }

    /**
     * 重置license
     *
     * @param id
     */
    public void reset(Integer id) {
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        Date now = new Date();

        licenseMapper.reset(id, key, now);
    }

    public LicenseDO findByKey(String license) {
        return licenseMapper.findByKey(license);
    }

    /**
     * 脱敏处理
     * 非当前登录人的license，一律脱敏
     *
     * @param userId
     * @param licenseKey
     * @return
     */
    private String desensitization(Integer userId, String licenseKey) {
        Integer currentUserId = SystemContextHolder.getUser().getId();
        if (currentUserId.equals(userId)) {
            return licenseKey;
        }
        return licenseKey.substring(0, 10) + "****" + licenseKey.substring(licenseKey.length() - 10);
    }

    /**
     * 服务端项目停止、启动时，更新在线状态为离线
     */
    @Init
    public void init() {
        licenseMapper.updateOnlineStatus(OnlineStatusEnum.OFFLINE.getStatus(), new Date());
    }


    @Override
    public void start() throws Throwable {

    }

    /**
     * 服务端项目停止、启动时，更新在线状态为离线
     */
    @Override
    public void stop() throws Throwable {
        licenseMapper.updateOnlineStatus(OnlineStatusEnum.OFFLINE.getStatus(), new Date());
    }

    /**
     * 查询当前角色下的license，若为管理员 则返回全部license
     */
    public List<LicenseListRes> queryCurUserLicense(LicenseListReq req) {
        if (SystemContextHolder.isAdmin()) {
            return this.list(req);
        }

        List<LicenseDO> list = licenseMapper.selectList(new LambdaQueryWrapper<LicenseDO>()
                .eq(LicenseDO::getEnable, EnableStatusEnum.ENABLE.getStatus())
                .eq(LicenseDO::getUserId, SystemContextHolder.getUserId())
        );
        List<LicenseListRes> licenseList = assembleConvertLicenses(list);
        return licenseList;
    }
}
