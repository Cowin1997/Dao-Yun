package cn.edu.fzu.dao;

import cn.edu.fzu.entity.CheckLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CheckLogMapper {

    @Select("select * from checklog where ch_checksid=#{sid} and ch_checkcouseid=#{cid};")
    public List<CheckLog> getCheckLogsBySidAndCid(Integer sid,Integer cid);


    @Select("select * from checklog where ch_checksid=#{sid} and ch_checkcouseid=#{Classid} and taskid=#{taskId};")
    public CheckLog isCheck(String sid,Integer taskId,Integer Classid);

    @Select("select * from checklog where ch_checksid=#{sid} and ch_checkcouseid=#{cid} and taskid=#{tid};")
    public CheckLog getCheckLogsBySid_Cid_Taskid(String sid,Integer cid,Integer tid);

    @Insert("insert into checklog (taskid,ch_checksid,ch_checkcouseid,ch_checkloc,ch_checkscore,ch_checktime) values(#{taskId},#{StudentId},#{classId},#{location},#{score},#{date});")
    public Boolean check(Integer taskId, String StudentId, Integer classId, String location, Integer score, Date date);
}
