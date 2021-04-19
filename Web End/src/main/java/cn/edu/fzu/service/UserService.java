package cn.edu.fzu.service;

import cn.edu.fzu.dao.StudentMapper;
import cn.edu.fzu.dao.TeacherMapper;
import cn.edu.fzu.dao.UserMapper;
import cn.edu.fzu.entity.Student;
import cn.edu.fzu.entity.Teacher;
import cn.edu.fzu.entity.User;
import cn.edu.fzu.param.RegistReq;
import cn.edu.fzu.param.RegistRes;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public ResultModel<RegistRes> regist(RegistReq req,String code){
        User user = this.userMapper.getUserByPhone(req.getPhone());
        if(user!=null){
            return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.RESIST_PHONE_EXIST,null);
        }
        else if(this.userMapper.getUserByName(req.getUsername())!=null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.RESIST_USERNAME_EXIST,null);
        else if(this.userMapper.getUserBySid(req.getIdentity())!=null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.RESIST_SID_EXIST,null);
        else{
            if(!req.getMsgcode().equals(code))  return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.INVLID_CODE,null);
            req.setRegistTime(new Date());
            if(req.getType()==1 || req.getType()==2) req.setIdentity(null);//管理员不设置学工号
            Boolean aboolean = this.userMapper.addUser(req);
            if(aboolean){
                if(req.getType()==3){
                    Teacher teacher = new Teacher();
                    teacher.setTe_id(req.getIdentity());
                    this.teacherMapper.addTeacher(teacher);
                }else if(req.getType()==4) {
                    Student student = new Student();
                    student.setSt_id(req.getIdentity());
                    log.info(student.toString());
                    this.studentMapper.addStudent(student);
                }
                return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.RESIST_SUCCESS,new RegistRes(req.getIdentity()));
            }else{
                return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.RESIST_ERROR,null);
            }
        }



    }
}
