package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {



    @Select("select * from course where cl_schoolcode=#{code} and cl_collegeid=#{colid} and cl_majorid=#{majid};")
    public List<Course> getCourseBySchCode_ColId_MajorId(Integer code,Integer colid,Integer majid);



    @Insert("insert into course(id,cl_classid,cl_classname,cl_teachid,cl_classloc,cl_begin,cl_end,cl_info,cl_createtime,cl_creatorid,cl_creatorname,cl_teachname,cl_schoolcode,cl_collegeid,cl_majorid)"
    +"values(#{id},#{cl_classid},#{cl_classname},#{cl_teachid},#{cl_classloc},#{cl_begin},#{cl_end},#{cl_info},#{cl_createtime},#{cl_creatorid},#{cl_creatorname},#{cl_teachname},#{cl_schoolcode},#{cl_collegeid},#{cl_majorid});")
    public Boolean addCourse(Course course);


    @Delete("delete from course where cl_classid=#{classId};")
    public Boolean delCourse(Integer classId);
}
