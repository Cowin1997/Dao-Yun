package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Insert("insert into student(id,st_id,st_name,st_sex,st_phone,st_email,st_schoolcode,st_collegecode,st_majorcode,st_checkscore,st_checkcount,st_lastchecktime,st_lastcheckloc,st_checklevel,st_info) " +
            "values(#{id},#{st_id},#{st_name},#{st_sex},#{st_phone},#{st_email},#{st_schoolcode},#{st_collegecode},#{st_majorcode},default,default,#{st_lastchecktime},#{st_lastcheckloc},#{st_checklevel},#{st_info});")
    public Boolean addStudent(Student student);


    @Select("select * from student where st_id=#{st_id};")
    public Student getStudent(String st_id);


}
