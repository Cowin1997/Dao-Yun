package cn.edu.fzu.dao;

import cn.edu.fzu.entity.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollegeMapper {


    @Select("select * from college where co_school = #{code} limit #{from},#{to};")
    public List<College> getCollegeBySchoolCode(Integer code,Integer from, Integer to);


    @Select("select * from college where co_code = #{code};")
    public College getCollegeByCollegeCode(Integer code);
    @Select("select count(*) from college where co_school = #{code};")
    public Integer getTotalBySchool(Integer code);
}
