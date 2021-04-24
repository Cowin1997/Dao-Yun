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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResultModel<LoginRes>> userLogin(@RequestBody LoginReq loginReq, HttpServletRequest request){
        ResultModel<LoginRes> resultModel = null;
        if(loginReq.getUsername()!=null) {
            resultModel = this.userService.loginByUsername(loginReq);
            return ResponseEntity.status(HttpStatus.OK).body(resultModel);
        }
        else if(loginReq.getPhone()!=null){
            HttpSession session = request.getSession(false);
            if(session==null||session.getAttribute(loginReq.getPhone())==null)
                return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.error(null,ResultUtils.MESSAGE.SESSION_ERROR));
                String smsCode = (String) session.getAttribute(loginReq.getPhone());
                resultModel = this.userService.loginByPhone(loginReq,smsCode);
                return ResponseEntity.status(HttpStatus.OK).body(resultModel);
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null,ResultUtils.MESSAGE.BAD_REQUEST));
    }





    @ResponseBody
    @RequestMapping(value = "/pwd-reset",method = RequestMethod.POST)
    @ApiOperation(value = "passwordReset",notes = "修改密码(登录的情况下修改,不同于忘记密码)")
    public ResponseEntity<ResultModel<String>> passwordReset(@RequestBody PassRestReq req){
        ResultModel resultModel = this.userService.passwordReset(req);
        return ResponseEntity.status(HttpStatus.OK).body(resultModel);
    }

    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ApiOperation(value = "regist",notes = "注册账号")
    public ResponseEntity<ResultModel<RegistRes>> regist(@RequestBody RegistReq req, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute(req.getPhone())==null)
            return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.error(null,ResultUtils.MESSAGE.SESSION_ERROR));
        String smsCode = (String) session.getAttribute(req.getPhone());
        ResultModel resultModel =  this.userService.regist(req,smsCode);
        return ResponseEntity.status(HttpStatus.OK).body(resultModel);
    }


    @ResponseBody
    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    @ApiOperation(value = "getSmsCode",notes = "请求短信验证")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL_SERVER_ERROR", response = ResultModel.class)
    })
    public ResponseEntity<ResultModel<String>> getSmsCode(@RequestBody SmsReq req,HttpServletRequest request){
        String smsCode = this.userService.getSmsCode(req.getPhone());
        if(smsCode!=null){ //成功
            HttpSession session = request.getSession(true); //创建会话
            session.setAttribute(req.getPhone(),smsCode); //手机和验证码映射
            return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(smsCode,ResultUtils.MESSAGE.GET_SUCCESS));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.error(null,ResultUtils.MESSAGE.GET_ERROR));
    }







    @ResponseBody
    @RequestMapping(value = "/pwd-forget",method = RequestMethod.POST)
    @ApiOperation(value = "pwdForget",notes = "短信验证找回密码")
    public ResponseEntity<ResultModel<Boolean>> pwdForget(@RequestBody PassForgetReq req,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute(req.getPhone())==null)
            return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.error(null,ResultUtils.MESSAGE.SESSION_ERROR));
        String smsCode = (String) session.getAttribute(req.getPhone());
        ResultModel<Boolean> resultModel = this.userService.pwdForget(req,smsCode);
        return ResponseEntity.status(HttpStatus.OK).body(resultModel);

    }



}
