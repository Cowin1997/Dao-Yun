package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Teacher;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from teacher where te_id=#{tid};")
    public Teacher getTeacherByTid(Integer tid);

    @Delete("delete from teacher where te_id=#{tid};")
    public Boolean deleteTeacherByTid(Integer tid);


    @Update("update teacher set te_name=#{te_name},te_phone=#{te_phone},te_email=#{te_email},te_sex=#{te_sex},te_info=#{te_info},te_rank=#{te_rank},te_edu=#{te_edu} where te_id=#{te_id};")
    public Boolean updateTeacher(Teacher teacher);

    //
//    @Select("select * from teacher where te_schoolcode=#{school} and te_collegeid=#{college} and te_majorid=#{major};")
//    public List<Teacher> getTeacherList(Integer school,Integer college,Integer major);
//
    @Insert("insert into teacher(te_id,te_name,te_phone,te_email,te_school,te_college,te_major,te_sex,te_info,te_rank,te_edu)" +
            "values(#{te_id},#{te_name},#{te_phone},#{te_email},#{te_school},#{te_college},#{te_major},#{te_sex},#{te_info},#{te_rank},#{te_edu});")
    public Boolean addTeacher(Teacher teacher);
}
