package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {

    @Select("select * from teacher where te_teachid=#{tid};")
    public Teacher getTeacherByTeaID(Integer tid);



}
