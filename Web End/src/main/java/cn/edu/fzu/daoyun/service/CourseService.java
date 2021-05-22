package cn.edu.fzu.daoyun.service;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.dto.CourseDTO;
import cn.edu.fzu.daoyun.dto.CourseDTO2;
import cn.edu.fzu.daoyun.entity.CourseDO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseService {
    // 根据班课号获取
    public CourseDO getCourseByCid(Integer cid);

    // 根据教师ID获取所有该教师班课
    public List<CourseDO> getCourseListByTid(Integer tid);

    // 添加班课信息
    public Boolean addCourse(CourseDO course);

    public Boolean deleteCourse(Integer cid);

    // 编辑班课信息
    public Boolean updateCourse(CourseDO course);

    // 根据学号获取学生的所有课程
    public List<CourseDTO> getCourseBySid(Integer sid);

    // 根据工号获取教师的所有课程
    public List<CourseDO> getCourseByTid(Integer tid);
    //
    public Page<CourseDTO2> getCourseListByOrg(Integer sch, Integer col, Integer maj, Integer page, Integer size);
}
