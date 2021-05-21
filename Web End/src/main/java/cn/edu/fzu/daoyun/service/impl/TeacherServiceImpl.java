package cn.edu.fzu.daoyun.service.impl;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.entity.StudentDO;
import cn.edu.fzu.daoyun.entity.TeacherDO;
import cn.edu.fzu.daoyun.exception.BadRequestException;
import cn.edu.fzu.daoyun.mapper.TeacherMapper;
import cn.edu.fzu.daoyun.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;



    public TeacherDO getTeacherByTid(Integer tid) {
       return this.teacherMapper.getTeacherByTid(tid);
    }


    public Boolean addTeacherByTid(TeacherDO teacher) {
        TeacherDO t = this.teacherMapper.getTeacherByTid(teacher.getTid());
        if(t!=null) throw new BadRequestException("该教师工号已经注册");
        teacher.setGmt_create(new Date());
        return this.teacherMapper.insertTeacher(teacher);
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
