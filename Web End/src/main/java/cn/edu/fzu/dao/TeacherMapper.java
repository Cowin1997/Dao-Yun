package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Teacher;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from teacher where te_teachid=#{tid};")
    public Teacher getTeacherByTeaID(Integer tid);

    @Select("select * from teacher where te_uid=#{id};")
    public Teacher getTeacherByUseId(Integer id);


    @Select("select * from teacher where id=#{id};")
    public Teacher getTeacherById(Integer id);

    @Delete("delete from teacher where id=#{id};")
    public Boolean delete(Integer id);


    @Delete("update teacher set te_teachname=#{te_teachname},te_phone=#{te_phone},te_email=#{te_email},te_sex=#{te_sex} where id=#{id};")
    public Boolean update(Teacher teacher);


    @Select("select * from teacher where te_schoolcode=#{school} and te_collegeid=#{college} and te_majorid=#{major};")
    public List<Teacher> getTeacherList(Integer school,Integer college,Integer major);

    @Insert("insert into teacher(te_teachid,te_teachname,te_phone,te_email,te_schoolcode,te_collegeid,te_majorid,te_sex,te_info,te_rank,te_edu,te_uid)" +
            "values(#{te_teachid},#{te_teachname},#{te_phone},#{te_email},#{te_schoolcode},#{te_collegeid},#{te_majorid},#{te_sex},#{te_info},#{te_rank},#{te_edu},#{te_uid});")
    public Boolean insert(Teacher teacher);
}
