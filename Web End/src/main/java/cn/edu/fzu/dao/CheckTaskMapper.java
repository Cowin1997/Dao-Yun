package cn.edu.fzu.dao;

import cn.edu.fzu.entity.CheckTask;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CheckTaskMapper {

    @Insert("insert into checktask(class_id,create_time,password,time_limit,teacher_id,type,info,status) values(#{class_id}," +
            "#{create_time},#{password},#{time_limit},#{teacher_id},#{type},#{info},#{status});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Integer addTask(CheckTask task);

    @Update("update checktask set status=1 where id=#{id};")
    public Boolean setTaskEnd(Integer id);


    @Select("select * from checktask where class_id=#{cid} order by create_time desc;")
    public List<CheckTask> getTaskListByClassId(String cid);
    @Select("select * from checktask where id=#{tid};")
    public CheckTask getTaskById(Integer tid);

    @Select("select * from checktask where status=0 and class_id=#{cid} order by create_time desc limit 1;")
    public CheckTask getUncheckTask(String cid);

    @Select("select status from checktask where id=#{id};")
    public Boolean isEnd(Integer id);

}
