package cn.edu.fzu.dao;

import cn.edu.fzu.entity.CheckLog;
import cn.edu.fzu.param.checkReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CheckLogMapper {






    @Insert("insert into checklog (ch_sid,ch_taskid,ch_checktime,ch_checkscore,ch_loc,ch_info) values(#{ch_sid},#{ch_taskid},#{ch_checktime},#{ch_checkscore},#{ch_loc},#{ch_info});")
    public Boolean check(checkReq req);

    @Select("select sum(checklog.ch_checkscore) from checklog,checktask where checklog.ch_sid=#{sid} and checktask.class_id=#{cid} and checktask.id=checklog.ch_taskid;")
    public Integer getSum(String sid,String cid);

    @Select("select checklog.id,checklog.ch_sid,checklog.ch_taskid,checklog.ch_checktime,checklog.ch_checkscore,checklog.ch_loc,checklog.ch_info from checklog,checktask where checklog.ch_sid=#{sid} and checktask.class_id=#{cid} and checktask.id=checklog.ch_taskid;")
    public List<CheckLog> getLogs(String sid,String cid);


    @Select("select * from checklog where ch_sid=#{sid} and ch_taskid=#{tid};")
    public CheckLog getLog(Integer tid,String sid);

}
