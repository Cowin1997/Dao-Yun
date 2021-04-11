package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Student;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("insert into student(id,st_id,st_name,st_sex,st_phone,st_email,st_schoolcode,st_collegecode,st_majorcode,st_checkscore,st_checkcount,st_lastchecktime,st_lastcheckloc,st_checklevel,st_info) " +
            "values(#{id},#{st_id},#{st_name},#{st_sex},#{st_phone},#{st_email},#{st_schoolcode},#{st_collegecode},#{st_majorcode},default,default,#{st_lastchecktime},#{st_lastcheckloc},#{st_checklevel},#{st_info});")
    public Boolean addStudent(Student student);


    @Select("select * from student where st_id=#{st_id};")
    public List<Student> getStudent(String st_id);

    @Select("select * from student where st_classid=#{classid};")
    public List<Student> getStudentByClassId(Integer classid);

    @Select("select * from student where st_classid=#{classid} order by st_id limit #{from},#{size};")
    public List<Student> getStudentByPage(Integer from, Integer size,Integer classid);


    @Select("select count(*) from student;")
    public Integer getStudentTotalNumber();


    @Delete("delete  from student where st_id=#{sid};")
    public Boolean delStudentBySid(Integer sid);


    @Update("update student set st_name=#{st_name}, st_sex=#{st_sex},st_phone=#{st_phone},st_email=#{st_email} where st_id=#{st_id};")
    public Boolean updateStudent(Student student);
}
