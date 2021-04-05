package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MajorMapper {

    @Select("select * from major where ma_schoolcode=#{sch_code} and ma_collegeid=#{col_id};")
    public List<Major> getMajorBySchooCodeAndCollegeId(Integer sch_code, Integer col_id);
}
