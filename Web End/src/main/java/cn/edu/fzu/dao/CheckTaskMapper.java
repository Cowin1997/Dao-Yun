package cn.edu.fzu.dao;

import cn.edu.fzu.entity.CheckTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CheckTaskMapper {

    @Insert("insert into checktask(class_id,create_time,password,time_limit,teacher_id,type,info) values(#{class_id}," +
            "#{create_time},#{password},#{time_limit},#{teacher_id},#{type},#{info});")
    public Boolean insert(CheckTask task);

    @Select("select * from checktask where status=1 limit 1;")
    @Results({
            @Result(property = "teacher",column = "teacher_id",one = @One(select = "cn.edu.fzu.dao.TeacherMapper.getTeacherById")),
            @Result(property = "course",column = "class_id",one = @One(select = "cn.edu.fzu.dao.CourseMapper.getCourseByClassId")),
    })
    public CheckTask getUncheckTask();


    @Select("select * from checktask where class_id=#{cid};")
    public List<CheckTask> getTaskList(Integer cid);
}
