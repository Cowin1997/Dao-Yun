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


    @Insert("insert into checklog (checksid,taskid,checkcid,checktime,checkscore,checkloc,info,longitude,latitude) values(#{checksid},#{taskid},#{checkcid},#{checktime},#{checkscore},#{loc},#{info},#{longitude},#{latitude});")
    public Boolean check(checkReq req);

    @Select("select sum(checklog.checkscore) from checklog,checktask where checklog.checksid=#{sid} and checktask.class_id=#{cid} and checktask.id=checklog.taskid;")
    public Integer getSum(String sid, String cid);

    @Select("select checklog.id,checklog.checksid,checklog.taskid,checklog.checktime,checklog.checkscore,checklog.loc,checklog.info checklog.longitude checklog.latitude from checklog,checktask where checklog.checksid=#{sid} and checktask.class_id=#{cid} and checktask.id=checklog.taskid;")
    public List<CheckLog> getLogs(String sid, String cid);


    @Select("select * from checklog where checksid=#{sid} and taskid=#{tid};")
    public CheckLog getLog(Integer tid, String sid);

}
