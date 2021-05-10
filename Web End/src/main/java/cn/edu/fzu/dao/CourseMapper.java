package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Course;
import cn.edu.fzu.entity.Student;
import cn.edu.fzu.param.CourseReq;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {


    @Select("select * from course where cs_id=#{cid};")
    public Course getCourseByCid(String cid);

    @Select("select * from course where cs_tid=#{tid};")
    public List<Course> getCourseByTid(String tid);

    @Insert("insert into course(cs_id,cs_name,cs_tid,cs_loc,cs_time,cs_status,cs_info) values (#{cs_id},#{cs_name},#{cs_tid},#{cs_loc},#{cs_time},#{cs_status},#{cs_info});")
    public Boolean addCourse(CourseReq c);

    @Update("update course set cs_name=#{cs_name},cs_loc=#{cs_loc},cs_time=#{cs_time},cs_status=#{cs_status},cs_info=#{cs_info} where cs_id=#{cs_id};")
    public Boolean updateCourse(CourseReq c);

    @Delete("delete from course where cs_id=#{cid};")
    public Boolean deleteCourse(String cid);

    @Select("select * from course where cs_school=#{sch} and cs_major=#{maj} and cs_college=#{col};")
    public List<Course> getCourseBySch_Col_Maj(Integer sch, Integer col, Integer maj);

    @Select("select * from selectcourse where st_id=#{sid} and cs_id=#{cid};")
    public Boolean isSelect(String sid, String cid);

    @Insert("insert into selectcourse(st_id,cs_id) values(#{sid},#{cid});")
    public Boolean Select(String sid, String cid);

    @Insert("delete from selectcourse where st_id=#{sid} and cs_id=#{cid};")
    public Boolean unSelect(String sid, String cid);

    @Select("select student.id,student.st_id,student.st_name,student.st_sex,student.st_phone,student.st_email from student,selectcourse where selectcourse.cs_id=#{cid} and selectcourse.st_id=student.st_id;")
    public List<Student> getStudentsByClassId(String cid);
}
