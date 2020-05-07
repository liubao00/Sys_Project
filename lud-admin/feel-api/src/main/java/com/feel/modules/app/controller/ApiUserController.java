package com.feel.modules.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.feel.common.annotation.Login;
import com.feel.common.form.FUserFrom;
import com.feel.common.form.FUserPwdFrom;
import com.feel.common.utils.PageUtils;
import com.feel.common.utils.R;
import com.feel.common.utils.SendMsg;
import com.feel.common.validator.ValidatorUtils;
import com.feel.modules.app.entity.FUserCodeEntity;
import com.feel.modules.app.entity.FUserEntity;
import com.feel.modules.app.entity.FUserRelationShipEntity;
import com.feel.modules.app.entity.FUserTeamScoreEntity;
import com.feel.modules.app.service.*;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */

@RestController
@RequestMapping("/api/f/fuser")
@Api(tags="Api用户信息(包括用户注册,用户信息修改)",value = "Api用户注册")
public class ApiUserController {

    private Logger logger = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    private FUserService fUserService;

    @Autowired
    private Producer producer;

    @Autowired
    private FUserCodeService userCodeService;

    @Autowired
    private FUserRelationShipService fUserRelationShipService;

    @Autowired
    private FRepeatService fRepeatService;

    @Autowired
    private FUserTeamScoreService  fUserTeamScoreService;

    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response,HttpServletRequest request)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取用户list列表接口", notes = "获取用户list列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "pageIndex", required = false, dataType = "int", paramType = "json"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "json")
    })
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fUserService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @Login
    @ApiOperation(value = "获取单个用户接口", notes = "获取单个用户接口")
    @GetMapping("/info/one")
    public R info(@ApiIgnore @RequestAttribute("userId") long userId){
        FUserEntity fUser = fUserService.getById(userId);

        return R.ok().put("fUser", fUser);
    }





    /**
     * 保存
     */
    @ApiOperation(value = "用户注册接口", notes = "用户注册接口")
    @PostMapping("/register")
    public R register(@RequestBody FUserFrom user,HttpServletRequest request){
        //表单校验
        ValidatorUtils.validateEntity(user);
        String code = user.getCode().trim();
        FUserCodeEntity codeEntity = userCodeService.getOne(new QueryWrapper<FUserCodeEntity>().eq("username", user.getUsername()));
        String codeStr="";
        if(codeEntity!=null){
            codeStr = codeEntity.getCode().trim();
            if(codeValidate(codeEntity)){
                return R.error("验证码错误");
            }
        }else{
            return R.error("验证码错误");
        }
        if(StringUtils.isEmpty(code) || !code.equals(codeStr)){
            return R.error("验证码错误");
        }
        FUserEntity fUser = new FUserEntity();
        BeanUtils.copyProperties(user,fUser);
        fUser.setLastLoginIp(getIpAddress(request));
        R result=fUserService.register(fUser);

        return result;
    }
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * 修改
     */
    @Login
    @ApiOperation(value = "用户信息修改接口", notes = "用户信息修改接口")
    @PostMapping("/update")
    public R update(@RequestBody FUserEntity fUser){
        //此接口不允许修改密码
        fUser.setPassword(null);

        ValidatorUtils.validateEntity(fUser);
        fUserService.updateById(fUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "用户信息删除接口", notes = "用户信息删除接口")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        fUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 发送短信
     */
    @ApiOperation(value = "注册发送短信验证码", notes = "注册发送短信验证码")
    @GetMapping("/sendMsg/{username}")
    public R sendMsg(@PathVariable("username") String username){
        FUserCodeEntity entity=userCodeService.getOne(new QueryWrapper<FUserCodeEntity>().eq("username", username));
        if(entity==null) {
             entity = new FUserCodeEntity();
        }
        Date nowTime =new Date();
        entity.setCreateTime(nowTime);
        entity.setUpdateTime(nowTime);
        entity.setType(1);
        entity.setUsername(username);
        //1:生成验证码
        int code=(int)((Math.random()*9+1)*1000);
        entity.setCode(code+"");
        if(entity.getId()==null) {
            userCodeService.save(entity);
        }else{
            userCodeService.updateById(entity);
        }
        try {
            SendMsg.message(username,code+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "用户密码修改", notes = "用户密码修改")
    @PostMapping("/modifyPwd")
    public R modifyPwd(@RequestBody FUserPwdFrom fUser){
        ValidatorUtils.validateEntity(fUser);
        String code=fUser.getCode().trim();
        FUserCodeEntity codeEntity=userCodeService.getOne(new QueryWrapper<FUserCodeEntity>().eq("username", fUser.getUsername()));
        String codeStr="";

        if(codeEntity!=null){
            codeStr=codeEntity.getCode().trim();
            if(codeValidate(codeEntity)){
                return R.error("验证码错误");
            }
        }else{
            return R.error("验证码错误");
        }
        if(StringUtils.isEmpty(code)||!code.equals(codeStr)){
            return R.error("验证码错误");
        }

        fUserService.modifyPwd(fUser);

        return R.ok();
    }

    /**
     * 返回true ,验证码超过2分钟,从新发送验证码
     * @param codeEntity
     * @return
     */
    public boolean codeValidate(FUserCodeEntity codeEntity){
        Long startTime=codeEntity.getCreateTime().getTime();
        Long endTime=new Date().getTime();
        int minutes = (int) ((endTime - startTime)/(1000 * 60));
        if(minutes>=2){
            return true;
        }
        return false;
    }

    /**
     * 直推、动态人数统计
     */
    @ApiOperation(value = "直推动态人数统计接口", notes = "直推动态人数统计")
    @GetMapping("/peoples")
    @Login
    public R peoples(@ApiIgnore @RequestAttribute("userId") long userId){
        try{
            //查询出所有直推用户信息
            List<FUserRelationShipEntity> list = fUserRelationShipService.list(new QueryWrapper<FUserRelationShipEntity>().eq("parent_id",userId));
            //存储所有直推用户ID
            List dUsersList = new ArrayList<>();
            list.stream().forEach(item -> {
                dUsersList.add(item.getUserId()+"");
            });

            String str = String.join(",", dUsersList);
            //有效直推人数
            int isDUser = fRepeatService.selectByIds(str);
            JSONObject json= new JSONObject();
            json.put("dUsers",list.size());
            json.put("isDUsers",isDUser);

            //查询出所有动态用户信息
            List<FUserRelationShipEntity> aList = fUserRelationShipService.list(new QueryWrapper<FUserRelationShipEntity>().like("id_key","-"+userId+"-"));
            //存储所有直推用户ID
            List aUsersList = new ArrayList<>();
            aList.stream().forEach(item -> {
                aUsersList.add(item.getUserId()+"");
            });

            String aStr = String.join(",", aUsersList);
            //有效动态人数
            int isAUser = fRepeatService.selectByIds(aStr);


            //获取AB区累计奖金
            List<FUserTeamScoreEntity> list1 = fUserTeamScoreService.list(new QueryWrapper<FUserTeamScoreEntity>().eq("user_id",userId));
            BigDecimal A = BigDecimal.ZERO;
            BigDecimal B = BigDecimal.ZERO;

            if(list1.size() >0){
                A = list1.stream().filter(i -> i.getSection() == 1).findAny().orElse(null) == null ? BigDecimal.ZERO : list1.stream().filter(i -> i.getSection() == 1).findAny().orElse(null).getGrandTotal();
                B = list1.stream().filter(i -> i.getSection() == 2).findAny().orElse(null) == null ? BigDecimal.ZERO : list1.stream().filter(i -> i.getSection() == 2).findAny().orElse(null).getGrandTotal();
            }

            json.put("AUsers",aList.size());
            json.put("isAUsers",isAUser);
            json.put("teamA",A.toPlainString());
            json.put("teamB",B.toPlainString());
            return R.ok().put("data",json);
        }catch (Exception e){
            logger.error("peoples: "+e.toString());
            return R.error(404,"出错，请联系管理员");
        }

    }

}
