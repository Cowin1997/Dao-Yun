package cn.edu.fzu.dao;

import cn.edu.fzu.entity.CheckLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckLogMapper {

    @Select("select * from checklog where ch_checksid=#{sid} and ch_checkcouseid=#{cid};")
    public List<CheckLog> getCheckLogsBySidAndCid(Integer sid,Integer cid);
}
