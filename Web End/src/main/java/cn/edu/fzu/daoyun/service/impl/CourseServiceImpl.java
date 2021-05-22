package cn.edu.fzu.daoyun.service.impl;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.dto.CourseDTO;
import cn.edu.fzu.daoyun.dto.CourseDTO2;
import cn.edu.fzu.daoyun.entity.CourseDO;
import cn.edu.fzu.daoyun.entity.StudentDO;
import cn.edu.fzu.daoyun.entity.TeacherDO;
import cn.edu.fzu.daoyun.exception.BadRequestException;
import cn.edu.fzu.daoyun.mapper.CourseMapper;
import cn.edu.fzu.daoyun.mapper.TeacherMapper;
import cn.edu.fzu.daoyun.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public CourseDO getCourseByCid(Integer cid) {
        return this.courseMapper.getCourseByCid(cid);
    }

    @Override
    public List<CourseDO> getCourseListByTid(Integer tid) {
        return this.courseMapper.getCourseListByTid(tid);
    }

    @Override
    public Boolean addCourse(CourseDO course) {
        course.setGmt_create(new Date());
        TeacherDO t = this.teacherMapper.getTeacherByTid(course.getTeacher_tid());
        if(t==null) throw new BadRequestException("教工号不存在");
        return this.courseMapper.addCourse(course);
    }

    @Override
    public Boolean deleteCourse(Integer cid) {
        return this.courseMapper.delCourse(cid);
    }

    @Override
    public Boolean updateCourse(CourseDO course) {
        course.setGmt_modified(new Date());
        return this.courseMapper.updateCourse(course);
    }

    @Override
    public List<CourseDTO> getCourseBySid(Integer sid) {
        return this.courseMapper.getCourseBySid(sid);
    }

    @Override
    public List<CourseDO> getCourseByTid(Integer tid) {
        return this.courseMapper.getCourseByTid(tid);
    }

    public Page<CourseDTO2> getCourseListByOrg(Integer sch, Integer col, Integer maj, Integer page, Integer size){
        Integer from = (page-1)*size;
        Integer to = page * size;
        Integer totalSize = this.courseMapper.getCourseListTotalByOrg(sch, col, maj);
        List<CourseDO> courseDOList =  this.courseMapper.getCourseListByOrg(sch, col, maj, from, to);
        Integer totalPage = (int) Math.ceil((double) totalSize / size);
        List<CourseDTO2> courseDTO2List = new ArrayList<>();
        for (CourseDO c : courseDOList) {
            CourseDTO2 d = new CourseDTO2();
            BeanUtils.copyProperties(c,d);
            TeacherDO teacher = this.teacherMapper.getTeacherByTid(c.getTeacher_tid());
            d.setTeacher(teacher);
            courseDTO2List.add(d);
        }
        return new Page<>(courseDTO2List,totalSize,totalPage);
    }


    public Page<CourseDTO2> getCourseListByLike(Integer page, Integer size,String search){
        Integer from = (page-1)*size;
        Integer to = page * size;
        List<CourseDO> courseDOList =  this.courseMapper.getCourseListByLike(from, to,search);
        Integer totalSize = courseDOList.size();
        Integer totalPage = (int) Math.ceil((double) totalSize / size);
        List<CourseDTO2> courseDTO2List = new ArrayList<>();
        for (CourseDO c : courseDOList) {
            CourseDTO2 d = new CourseDTO2();
            BeanUtils.copyProperties(c,d);
            TeacherDO teacher = this.teacherMapper.getTeacherByTid(c.getTeacher_tid());
            d.setTeacher(teacher);
            courseDTO2List.add(d);
        }
        return new Page<>(courseDTO2List,totalSize,totalPage);
    }
}
