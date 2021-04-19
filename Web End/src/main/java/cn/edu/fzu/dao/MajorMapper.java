package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MajorMapper {
    @Select("select count(*) from major where ma_school=#{sch_code} and ma_college=#{col_id};")
    public Integer getTotalBySchoolAndCollegeId(Integer sch_code, Integer col_id);

    @Select("select * from major where ma_code=#{code};")
    public Major getMajorByMajorCode(String code);

    @Select("select * from major where ma_school=#{sch_code} and ma_college=#{col_id} limit #{from},#{to};")
    public List<Major> getMajorBySchooCodeAndCollegeId(Integer sch_code, Integer col_id,Integer from, Integer to);
}
