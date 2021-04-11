package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {



    @Select("select * from course where cl_schoolcode=#{code} and cl_collegeid=#{colid} and cl_majorid=#{majid};")
    @Results({
            @Result(property = "teacher",column = "cl_teachid",one = @One(select = "cn.edu.fzu.dao.TeacherMapper.getTeacherById"))
    })
    public List<Course> getCourseBySchCode_ColId_MajorId(Integer code,Integer colid,Integer majid);



    @Insert("insert into course(id,cl_classid,cl_classname,cl_teachid,cl_classloc,cl_begin,cl_end,cl_info,cl_createtime,cl_creatorid,cl_creatorname,cl_teachname,cl_schoolcode,cl_collegeid,cl_majorid)"
    +"values(#{id},#{cl_classid},#{cl_classname},#{cl_teachid},#{cl_classloc},#{cl_begin},#{cl_end},#{cl_info},#{cl_createtime},#{cl_creatorid},#{cl_creatorname},#{cl_teachname},#{cl_schoolcode},#{cl_collegeid},#{cl_majorid});")
    public Boolean addCourse(Course course);


    @Delete("delete from course where cl_classid=#{classId};")
    public Boolean delCourse(Integer classId);


    @Select("select * from course where cl_teachid=#{tid};")
    public List<Course> getCourseByTid(Integer tid);

    @Select("update course,teacher set course.cl_classname=#{cl_classname},course.cl_classloc=#{cl_classloc},course.info=#{info},teacher.te_teachname=#{teacher.te_teachname},teacher.te_phone=#{teacher.te_phone} where teacher.id=#{teacher.id} and  course.id=#{id} ;")
    public Boolean update(Course course);


    @Delete("delete from course where id=#{id};")
    public Boolean delete(Integer id);
}
