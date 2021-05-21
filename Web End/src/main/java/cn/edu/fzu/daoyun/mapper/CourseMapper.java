package cn.edu.fzu.daoyun.mapper;

import cn.edu.fzu.daoyun.dto.CourseDTO;
import cn.edu.fzu.daoyun.dto.StudentCheckLogDTO;
import cn.edu.fzu.daoyun.entity.CourseDO;
import cn.edu.fzu.daoyun.entity.StudentDO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    // 根据班课号获取
    @Select("select * from course where cid=#{cid};")
    public CourseDO getCourseByCid(Integer cid);

    // 根据教师ID获取所有该教师班课
    @Select("select * from course where teacher_id = #{tid};")
    public List<CourseDO> getCourseListByTid(Integer tid);

    // 添加班课信息
    @Insert("insert into course (cid,teacher_tid,enabled,coursename,detailinfo,school_code,college_code,major_code,gmt_create) " +
            "values (#{cid},#{teacher_tid},#{enabled},#{coursename},#{detailinfo},#{school_code},#{college_code},#{major_code},#{gmt_create});")
    public Boolean addCourse(CourseDO course);

    // 编辑班课信息
    @Insert("update course set coursename=#{coursename},enabled=#{enabled},detailinfo=#{detailinfo}, teacher_tid=#{teacher_tid} where cid = #{cid};")
    public Boolean updateCourse(CourseDO course);


    // 根据学号获取学生的所有课程
    @Select("select s.*, sc.score,sc.totalcheck,sc.hascheck\n" +
            " from student s,select_course sc where s.sid = sc.student_sid and s.sid = #{sid};")
    public List<CourseDTO> getCourseBySid(Integer sid);


    // 根据学号获取教师的所有课程
    @Select("select * from course where teacher_tid=#{tid};")
    public List<CourseDO> getCourseByTid(Integer tid);

    @Select("select s.*, sc.score,sc.totalcheck, sc.hascheck from student s,select_course sc where s.sid=sc.student_sid and sc.course_cid = #{cid}; ")
    public List<StudentCheckLogDTO> getStudentByClassId(Integer cid);

    @Insert("insert into select_course(course_cid,student_sid,score,totalcheck,hascheck) " +
            "values (#{cid},#{sid},0,0,0);")
    public Boolean selectCourse(Integer cid,Integer sid);

    @Delete("delete from select_course where course_cid=#{cid} and student_sid=#{sid};")
    public Boolean deleteCourse(Integer cid,Integer sid);


    @Select("select count(*) from course where school_code=#{sch} and college_code = #{col} and major_code=#{maj};")
    public Integer getCourseListTotalByOrg(Integer sch,Integer col,Integer maj);

    @Select("select * from course where school_code=#{sch} and college_code = #{col} and major_code=#{maj} limit #{from},#{to};")
    public List<CourseDO> getCourseListByOrg(Integer sch,Integer col,Integer maj,Integer from,Integer to);

    @Update("update course set enabled = #{enabled} where cid =#{cid};")
    public Boolean updateStatus(Integer cid, Boolean enabled);
}
