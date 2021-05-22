package cn.edu.fzu.daoyun.service.impl;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.entity.StudentDO;
import cn.edu.fzu.daoyun.entity.TeacherDO;
import cn.edu.fzu.daoyun.entity.UserAuthDO;
import cn.edu.fzu.daoyun.entity.UserDO;
import cn.edu.fzu.daoyun.exception.BadRequestException;
import cn.edu.fzu.daoyun.mapper.TeacherMapper;
import cn.edu.fzu.daoyun.mapper.UserAuthMapper;
import cn.edu.fzu.daoyun.mapper.UserMapper;
import cn.edu.fzu.daoyun.query.AddTeacherQuery;
import cn.edu.fzu.daoyun.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAuthMapper userAuthMapper;


    public TeacherDO getTeacherByTid(Integer tid) {
       return this.teacherMapper.getTeacherByTid(tid);
    }


    public Boolean addTeacher(AddTeacherQuery query) {
        TeacherDO t = this.teacherMapper.getTeacherByTid(query.getTid());
        if(t!=null) throw new BadRequestException("该教师工号已经注册");
        // 注册检查
        UserDO u = this.userMapper.getUserByPhone(query.getPhone());
        UserAuthDO u2 = this.userAuthMapper.selectByIdentifier(query.getPhone(), null);
        if(u!=null || u2!=null) throw new BadRequestException("电话号码已被注册");
        // 注册
        query.setGmt_create(new Date());
        Boolean aBoolean = this.userMapper.addUser2(query);
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        // 添加凭证
        aBoolean = this.userAuthMapper.addUserAuthLocal2(query.getId(), query.getAccountName(), query.getGmt_create());
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        aBoolean = this.userAuthMapper.addUserAuthPhone2(query.getId(), query.getPhone(), query.getGmt_create());
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        // 添加学生
        TeacherDO teacher = new TeacherDO();
        BeanUtils.copyProperties(query,teacher);
        teacher.setUser_id(query.getId());
        aBoolean = this.teacherMapper.insertTeacher(teacher);
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        return aBoolean;
    }



    public Page<TeacherDO> getTeacherList(Integer sch, Integer col, Integer maj, Integer page, Integer size) {
        Integer from = (page-1)*size;
        Integer to = page * size;
        Integer totalSize = this.teacherMapper.getTotalBySch_Col_Maj(sch,col,maj);
        Integer totalPage = (int) Math.ceil((double) totalSize / size);
        List<TeacherDO> pageData = this.teacherMapper.getTeacherList(sch, col, maj, from, to);
        return new Page<>(pageData,totalSize,totalPage);
    }


    public Boolean delTeacherByTid(Integer tid) {
        return this.teacherMapper.deleteTeacherByTid(tid);
    }


    public Boolean updTeacherByTid(TeacherDO teacher) {
        return this.teacherMapper.updateTeacher(teacher);
    }






    public Page<TeacherDO> searchTeacherByLike(Integer page, Integer size,String search){
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<TeacherDO> teacherByLike = this.teacherMapper.getTeacherByLike(search);
        Integer totalSize = teacherByLike.size(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        Page pageResult = new Page(teacherByLike, totalSize, totalPage);
        return pageResult;
    }




}
