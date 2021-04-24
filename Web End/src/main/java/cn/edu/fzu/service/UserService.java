package cn.edu.fzu.service;

import cn.edu.fzu.dao.StudentMapper;
import cn.edu.fzu.dao.TeacherMapper;
import cn.edu.fzu.dao.UserMapper;
import cn.edu.fzu.entity.Student;
import cn.edu.fzu.entity.Teacher;
import cn.edu.fzu.entity.User;
import cn.edu.fzu.param.*;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import cn.edu.fzu.utils.SmsUtils;
import cn.edu.fzu.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
@Slf4j
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TeacherMapper teacherMapper;

    @Transactional
    public ResultModel<RegistRes> regist(RegistReq req, String smsCode) {
        User user = this.userMapper.getUserByPhone(req.getPhone());
        if (user != null) {
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.RESIST_PHONE_EXIST, null);
        } else if (this.userMapper.getUserByName(req.getUsername()) != null)
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.RESIST_USERNAME_EXIST, null);
        else if (this.userMapper.getUserBySid(req.getIdentity()) != null)
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.RESIST_SID_EXIST, null);
        else {
            if (!StringUtils.equals(req.getMsgcode(), smsCode))
                return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.INVLID_CODE, null);
            req.setRegistTime(new Date()); //设置注册时间
            if (req.getType() == 1 || req.getType() == 2) req.setIdentity(null); //管理员不设置学工号
            Boolean aboolean = this.userMapper.addUser(req);
            if (aboolean) {
                if (req.getType() == 3&& req.getIdentity()!=null) { //教师注册
                    Teacher teacher = new Teacher();
                    teacher.setTe_id(req.getIdentity());
                    teacher.setTe_phone(req.getPhone());
                    teacher.setTe_email(req.getEmail());
                    this.teacherMapper.addTeacher(teacher);
                } else if (req.getType() == 4 && req.getIdentity()!=null) {
                    Student student = new Student();
                    student.setSt_id(req.getIdentity());
                    student.setSt_phone(req.getPhone());
                    student.setSt_email(req.getEmail());
                    this.studentMapper.addStudent(student);
                }
                return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.RESIST_SUCCESS, new RegistRes(req.getIdentity()));
            } else {
                return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.RESIST_ERROR, null);
            }
        }
    }

    public String getSmsCode(String phone) {
        String smsCode = SmsUtils.genSmsCode(); //后端生成随机4位验证码
        String smsResp = SmsUtils.getSmsCode(phone, smsCode); //请求API发送验证码到手机
        log.warn(">>>>>>获取验证码: " + phone + "......." + smsResp);
        if (smsResp != null) return smsCode;
        return null;
    }

    public ResultModel<String> passwordReset(PassRestReq req) {
        User user = this.userMapper.getUserById(req.getId());
        if (user != null) {
            if (!req.getOldPass().equals(user.getUs_password())) {
                return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.UPDATE_ERROR, "");
            } else {
                Boolean aBoolean = this.userMapper.userPasswordResetById(req.getNewPass(), req.getId());
                if (aBoolean) {
                    return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.UPDATE_SUCCESS, "");
                }
            }
        }
        return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.UPDATE_ERROR, "");
    }


    public ResultModel<Boolean> pwdForget(PassForgetReq req, String smsCode) {
        if (!StringUtils.equals(req.getNewPass(), req.getNewPassAgain()))
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.PASSWORD_NOT_CONSISTENT, false);
        if (!StringUtils.equals(req.getCode(), smsCode))
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.INVLID_CODE, false);
        else {
            Boolean aBoolean = this.userMapper.userPasswordResetByPhone(req.getPhone(), req.getNewPassAgain());
            if (aBoolean)
                return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.UPDATE_SUCCESS, true);
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.UPDATE_ERROR, false);
        }
    }


    public ResultModel<LoginRes> loginByUsername(LoginReq loginReq) {
        //用户名密码登录
        User user = this.userMapper.getUserByNameAndPassWord(loginReq.getUsername(), loginReq.getPassword());
        if (user != null) {
            String token = TokenUtils.getToken(user);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.LOGIN_SUCCESS, new LoginRes(token, user.getUs_roleid(), user.getUs_id()));
        } else {
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.LOGIN_ERROR_ACCOUNT_PASS, new LoginRes(null, null, null));
        }
    }

    public ResultModel<LoginRes> loginByPhone(LoginReq loginReq,String smsCode){
        if(!StringUtils.equals(loginReq.getCode(),smsCode)) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.INVLID_CODE,new LoginRes(null,null,null));
        else{
            User user = this.userMapper.getUserByPhone(loginReq.getPhone());
            if(user==null)  return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.ACCOUNT_NOT_EXIST,null);
            String token = TokenUtils.getToken(user);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.LOGIN_SUCCESS,new LoginRes(token,user.getUs_roleid(),user.getUs_id()));
        }
    }


}

