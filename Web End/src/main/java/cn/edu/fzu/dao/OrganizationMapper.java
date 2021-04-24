package cn.edu.fzu.dao;

import cn.edu.fzu.entity.College;
import cn.edu.fzu.entity.Major;
import cn.edu.fzu.entity.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    //获取学校分页列表
    @Select("select * from Organization where type=1 limit #{from},#{to};")
    @Results(id = "schoolMap",
    value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "sch_name"),
            @Result(column = "code", property = "sch_code"),
            @Result(column = "desc", property = "sch_info")
    })
    public List<School> getSchoolList(Integer from,Integer to);


    //获取所有学校个数
    @Select("select count(*) from Organization where type=1;")
    public Integer getSchoolTotal();

    //根据学校代码获取学校信息
    @Select("select * from Organization where code=#{schCode};")
    @ResultMap(value = {"schoolMap"})
    public School getSchool(Integer schCode);


    //根据学校代码,获取学校相关专业
    @Select("select * from Organization where type=2 and parentId=#{schCode} limit #{from},#{to};")
    @Results(id = "collegeMap",value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "co_name"),
            @Result(column = "code", property = "co_code"),
            @Result(column = "desc", property = "co_info")
    })
    public List<College> getCollegeList(Integer schCode ,Integer from, Integer to);

    //获取学校所开设的学院个数
    @Select("select count(*) from Organization where type=2 and parentId=#{schCode};")
    public Integer getCollegeTotal(Integer schCode);

    //根据学校代码和学院代码,获取学院详细信息
    @Select("select * from Organization where type=2 and parentId=#{schCode} and code=#{colCode};")
    @ResultMap(value = {"collegeMap"})
    public College getCollege(Integer schCode,Integer colCode);



    //根据学校代码和学院代码,获取专业列表信息
    //根据学校代码,获取学校相关专业
    @Select("select * from Organization where type=3 and parentId=#{colCode} limit #{from},#{to};")
    @Results(id = "majorMap",value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "ma_name"),
            @Result(column = "code", property = "ma_code"),
            @Result(column = "desc", property = "ma_info")
    })
    public List<Major> getMajorList(Integer colCode, Integer from, Integer to);


    //获取专业个数
    @Select("select count(*) from Organization where type=3 and parentId=#{colCode} ;")
    public Integer getMajorTotal(Integer colCode);


    @Select("select * from Organization where type=3 and parentId=#{colCode} and code=#{majCode};")
    @ResultMap(value = {"majorMap"})
    public Major getMajor(Integer colCode,Integer majCode);
}
