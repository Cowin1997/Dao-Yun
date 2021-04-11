package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {

    @Select("select * from teacher where te_teachid=#{tid};")
    public Teacher getTeacherByTeaID(Integer tid);

    @Select("select * from teacher where te_uid=#{id};")
    public Teacher getTeacherByUseId(Integer id);


    @Select("select * from teacher where id=#{id};")
    public Teacher getTeacherById(Integer id);
}
