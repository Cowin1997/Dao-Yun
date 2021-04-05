package cn.edu.fzu.dao;

import cn.edu.fzu.entity.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollegeMapper {


    @Select("select * from college where co_schoolcode = #{code};")
    public List<College> getCollegeBySchoolCode(Integer code);


    @Select("select * from college where co_collegeid = #{id};")
    public List<College> getCollegeByCollegeId(Integer id);
}
