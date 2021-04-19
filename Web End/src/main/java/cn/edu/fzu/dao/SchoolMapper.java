package cn.edu.fzu.dao;

import cn.edu.fzu.entity.School;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SchoolMapper {

    @Select("select * from school limit #{from},#{to};")
    public List<School> getSchools(Integer from,Integer to);

    @Select("select * from school where sch_code = #{code};")
    public School getSchoolBySchoolCode(Integer code);

    @Select("select count(*) from school;")
    public Integer getSchoolTotal();
}
