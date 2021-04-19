package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Student;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface StudentMapper {


    @Select("select * from student where st_id=#{st_id};")
    public Student getStudent(String st_id);

    @Insert("insert into student(id,st_id,st_name,st_sex,st_phone,st_email,st_school,st_college,st_major,st_grade) " +
            "values(#{id},#{st_id},#{st_name},#{st_sex},#{st_phone},#{st_email},#{st_school},#{st_college},#{st_major},#{st_grade});")
    public Boolean addStudent(Student student);

    @Select("select count(*) from selectcourse where cs_id=#{cid};")
    public Integer getTotalByCid(String cid);

    @Select("select st_id from selectcourse where cs_id=#{cid} limit #{from},#{to};")
    public List<String> getStudentSidByCid(String cid,Integer from,Integer to);

    @Select("Select * from student where st_id=#{sid};")
    public Student getStudentBySid(String sid);

    @Select("select count(*) from student where st_school=#{sch};")
    public Integer getTotalBySch(Integer sch);
    @Select("Select * from student where st_school=#{sch} limit #{from},#{to};")
    public List<Student> getStudentBySch(Integer sch,Integer from,Integer to);

    @Select("select count(*) from student where st_school=#{sch} and st_college=#{col};")
    public Integer getTotalBySch_Col(Integer sch,Integer col);
    @Select("Select * from student where st_school=#{sch} and st_college=#{col} limit #{from},#{to};")
    public List<Student> getStudentBySch_Col(Integer sch, Integer col,Integer from,Integer to);

    @Select("select count(*) from student where st_school=#{sch} and st_college=#{col} and st_major=#{maj};")
    public Integer getTotalBySch_Col_Maj(Integer sch, Integer col,Integer maj);
    @Select("Select * from student where st_school=#{sch} and st_college=#{col} and st_major=#{maj} limit #{from},#{to};")
    public List<Student> getStudentBySch_Col_Maj(Integer sch, Integer col,Integer maj,Integer from,Integer to);



    @Delete("delete  from student where st_id=#{sid};")
    public Boolean delStudentBySid(String sid);


    @Update("update student set st_name=#{st_name}, st_sex=#{st_sex},st_phone=#{st_phone},st_email=#{st_email},st_school=#{st_school},st_college=#{st_college},st_major=#{st_major},st_grade=#{st_grade} where st_id=#{st_id};")
    public Boolean updateStudent(Student student);
}
