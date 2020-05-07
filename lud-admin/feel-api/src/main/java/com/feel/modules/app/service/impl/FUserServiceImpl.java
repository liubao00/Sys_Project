package com.feel.modules.app.service.impl;

import com.feel.common.exception.RRException;
import com.feel.common.form.FUserPwdFrom;
import com.feel.common.form.LoginForm;
import com.feel.common.utils.*;
import com.feel.common.validator.Assert;
import com.feel.modules.app.dao.FUserRelationShipDao;
import com.feel.modules.app.dao.FUserScoreDao;
import com.feel.modules.app.entity.*;
import com.feel.modules.app.service.BAddressEthService;
import com.feel.modules.app.service.BAddressUsdtService;
import com.feel.modules.app.service.TokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.feel.modules.app.dao.FUserDao;
import com.feel.modules.app.service.FUserService;
import org.springframework.transaction.annotation.Transactional;


@Service("fUserService")
public class FUserServiceImpl extends ServiceImpl<FUserDao, FUserEntity> implements FUserService {
    @Autowired
    private FUserDao userDao;
    @Autowired
    private FUserScoreDao userScoreDao;
    @Autowired
    private FUserRelationShipDao userRelationShipDao;

    @Autowired
    private BAddressUsdtService bAddressUsdtService;

    @Autowired
    private BAddressEthService bAddressEthService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FUserEntity> page = this.page(
                new Query<FUserEntity>().getPage(params),
                new QueryWrapper<FUserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public FUserEntity queryUserByName(@Param("userName") String userName) {
        return baseMapper.queryUserByName(userName);
    }


    /**
     * 注册
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public R register(FUserEntity user){
        //用户信息
        Date nowTime = TimeUtils.getDateNow();
        user.setCreateTime(nowTime);
        user.setUpdateTime(nowTime);
        user.setLastLoginTime(nowTime);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
//        user.setPayPassword(DigestUtils.sha256Hex(user.getPayPassword()));
        user.setStatus(1);
        user.setAddressUsdt(bAddressUsdtService.getAddress());
        user.setAddressEth(bAddressEthService.getAddress());
        String inviCode = user.getInvitationCode();

        //设置邀请码(唯一)
        String invitationCode ;
        while (true) {
            invitationCode = RandomUtil.getStringRandom(8);
            if (invitation_code_check(invitationCode)) {
                break;
            }
        }
        user.setInvitationCode(invitationCode);

        //用户注册校验
        if(StringUtils.isNull(user.getAddressEth())) {
            return R.error("获取地址失败~!");
        }
        if(StringUtils.isNull(user.getAddressUsdt())) {
            return R.error("获取地址失败~!");
        }

        if(StringUtils.isNull(user.getInvitationCode())) {
            return R.error("获取邀请码失败~!");
        }


        userDao.insert(user);

        user = userDao.selectOne(new QueryWrapper<FUserEntity>().eq("invitation_code",invitationCode));

        //用户层级
        Integer userId = user.getId();
        FUserRelationShipEntity userRelationShipEntity = new FUserRelationShipEntity();
        userRelationShipEntity.setUserId(userId);
        FUserEntity parentUser = userDao.selectOne(new QueryWrapper<FUserEntity>().eq("invitation_code",inviCode));
        //查找父级层级关系
        if(parentUser==null){
            return R.error("邀请码错误,请重新输入");
        }
        FUserRelationShipEntity parentUserRelationShip = userRelationShipDao.selectOne(new QueryWrapper<FUserRelationShipEntity>().eq("user_id",parentUser.getId()));


//        parentUserRelationShip.setIdKey(parentUserRelationShip.getIdKey() + "-" + user.getId());
//        userRelationShipDao.updateById(parentUserRelationShip);

        userRelationShipEntity.setParentId(parentUser.getId());
        userRelationShipEntity.setIdKey(parentUserRelationShip.getIdKey() + "-" + user.getId());
        userRelationShipEntity.setCreateTime(nowTime);
        userRelationShipEntity.setUpdateTime(nowTime);
        userRelationShipDao.insert(userRelationShipEntity);

        //用户积分
        FUserScoreEntity scoreEntity = new FUserScoreEntity();
        scoreEntity.setUpdateTime(nowTime);
        scoreEntity.setCreateTime(nowTime);
        scoreEntity.setUserId(userId);
        scoreEntity.setEarningsScore(new BigDecimal(0));
        scoreEntity.setFreezeScore(new BigDecimal(0));
        scoreEntity.setScore(new BigDecimal(0));
        scoreEntity.setScoreType(2);
        userScoreDao.insert(scoreEntity);

        String phone=user.getUsername();
        String hidenPhone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        user.setUsername(hidenPhone);
        return R.ok().put("user",user);
    }


    /**
     * 设置邀请码(唯一)
     * @param invitationCode
     * @return
     */
    private boolean invitation_code_check(String invitationCode) {
        FUserEntity list_invitationCode = userDao.selectOne(new QueryWrapper<FUserEntity>().eq("invitation_code",invitationCode));
        if (list_invitationCode == null) {
            return true;
        } else {
            return false;
        }
    }

    @Autowired
    private TokenService tokenService;

    @Override
    public FUserEntity queryByMobile(String username) {
        return baseMapper.selectOne(new QueryWrapper<FUserEntity>().eq("username", username));
    }

    @Override
    public Map<String, Object> login(LoginForm form) {
        FUserEntity user = queryByMobile(form.getMobile());
        Assert.isNull(user, "手机号或密码错误");

        //密码错误
        if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
            throw new RRException("手机号或密码错误");
        }

        //获取登录token
        TokenEntity tokenEntity = tokenService.createToken(user.getId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("userinfo",user);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());

        return map;
    }

    /**
     * 修改用户密码
     */
    public void modifyPwd(FUserPwdFrom userPwdFrom){

        FUserEntity entity=queryByMobile(userPwdFrom.getUsername());
        if(entity!=null) {
            entity.setPassword(DigestUtils.sha256Hex(userPwdFrom.getPassword()));
            userDao.updateById(entity);
        }
    }
}
