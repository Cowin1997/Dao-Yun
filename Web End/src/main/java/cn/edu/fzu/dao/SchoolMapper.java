package cn.edu.fzu.dao;

import cn.edu.fzu.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SchoolMapper {

    @Select("select * from school;")
    public List<School> getSchools();

    @Select("select * from school where sch_code = #{code};")
    public School getSchoolBySchoolCode(Integer code);
}
