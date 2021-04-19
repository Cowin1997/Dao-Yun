package cn.edu.fzu.controller;

import cn.edu.fzu.dao.UserMapper;
import cn.edu.fzu.entity.User;
import cn.edu.fzu.param.*;
import cn.edu.fzu.service.UserService;
import cn.edu.fzu.utils.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequestMapping(value = {"/user"})
@Api(tags={"用户账号接口"})
public class UserController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "userLogin",notes = "普通登录接口,若用户名不为空,则使用用户名密码登录。若电话号码不为空,则使用短信验证")
    public ResultModel<LoginRes> userLogin(@RequestBody LoginReq loginReq, HttpServletRequest request){
        if(loginReq.getUsername()!=null){ //用户名密码登录
            User user  = this.userMapper.getUserByNameAndPassWord(loginReq.getUsername(), loginReq.getPassword());
            if(user!=null){
                String token = TokenUtils.getToken(user);
                return new ResultModel<LoginRes>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.LOGIN_SUCCESS,new LoginRes(token,user.getUs_roleid(),user.getUs_id()));
            }else{
                return new ResultModel<LoginRes>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.LOGIN_ERROR_ACCOUNT_PASS,new LoginRes(null,null,null));
            }
        }else if(loginReq.getPhone()!=null){//手机号码登录
            String phone  = loginReq.getPhone();
            HttpSession session = request.getSession(false);
            if(session==null||session.getAttribute(loginReq.getPhone())==null) return new ResultModel<LoginRes>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SESSION_ERROR,null);
            String smsCode = (String) session.getAttribute(loginReq.getPhone());
            log.info(">>>>>userLogin:loginReq="+loginReq.getCode()+",smsCode="+smsCode);
            if(!loginReq.getCode().equals(smsCode)) return new ResultModel<LoginRes>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.INVLID_CODE,new LoginRes(null,null,null));
            else{
                User user = this.userMapper.getUserByPhone(phone);
                if(user==null)  return new ResultModel<LoginRes>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.ACCOUNT_NOT_EXIST,null);
                String token = TokenUtils.getToken(user);
                return new ResultModel<LoginRes>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.LOGIN_SUCCESS,new LoginRes(token,user.getUs_roleid(),user.getUs_id()));
            }
        }
        return new ResultModel<LoginRes>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.LOGIN_ERROR_ACCOUNT_PASS,new LoginRes(null,null,null));
    }

    @ResponseBody
    @RequestMapping(value = "/pwd-reset",method = RequestMethod.POST)
    @ApiOperation(value = "passwordReset",notes = "修改密码(登录的情况下修改,不同于忘记密码)")
    public ResultModel<String> passwordReset(@RequestBody PassRestReq req){
        User user = this.userMapper.getUserById(req.getId());
        if(user!=null){
            if(!req.getOldPass().equals(user.getUs_password())){
               return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,"");
            }else {
                Boolean aBoolean = this.userMapper.userPasswordResetById(req.getNewPass(), req.getId());
                if(aBoolean){
                    return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.UPDATE_SUCCESS,"");
                }
            }
        }
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,"");
    }

    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ApiOperation(value = "regist",notes = "注册账号")
    public ResultModel<RegistRes> regist(@RequestBody RegistReq req,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute(req.getPhone())==null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SESSION_ERROR,null);
        String smsCode = (String) session.getAttribute(req.getPhone());
        return this.userService.regist(req,smsCode);
    }


    @ResponseBody
    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    @ApiOperation(value = "getSmsCode",notes = "请求短信验证")
    public ResultModel<String> getSmsCode(@RequestBody SmsReq req,HttpServletRequest request){
        String smsCode = SmsUtils.genSmsCode();
        log.info(">>>>>> generate smsCode:"+smsCode);
        String smsResp = "4856";//SmsUtils.getSmsCode(req.getPhone(),smsCode);
        log.info(">>>>>> get smsResp:"+smsResp);
        if(smsResp!=null){
//            JSONObject object = JSON.parseObject(smsResp);
//            log.info(object.toString());
            HttpSession session = request.getSession(true);
            session.setAttribute(req.getPhone(),smsCode);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,smsCode);
        }

        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.GET_ERROR,null);
    }







    @ResponseBody
    @RequestMapping(value = "/pwd-forget",method = RequestMethod.POST)
    @ApiOperation(value = "pwdForget",notes = "短信验证找回密码")
    public ResultModel<Boolean> pwdForget(@RequestBody PassForgetReq req,HttpServletRequest request){
        if(!req.getNewPass().equals(req.getNewPassAgain())) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.PASSWORD_NOT_CONSISTENT,false);
        HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute(req.getPhone())==null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SESSION_ERROR,null);
        String smsCode = (String) session.getAttribute(req.getPhone());
        if(!req.getCode().equals(smsCode)) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.INVLID_CODE,false);
        else {
            Boolean aBoolean = this.userMapper.userPasswordResetByPhone(req.getPhone(),req.getNewPassAgain());
            if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.UPDATE_SUCCESS,true);
            return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,false);
        }
    }



}
