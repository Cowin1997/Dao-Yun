package cn.edu.fzu.daoyun.mapper;

import cn.edu.fzu.daoyun.dto.CheckLogDTO;
import cn.edu.fzu.daoyun.dto.StudentCheckLogDTO;
import cn.edu.fzu.daoyun.entity.CheckLog;
import cn.edu.fzu.daoyun.entity.TaskDO;
import cn.edu.fzu.daoyun.query.CheckQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CheckMapper {

    @Insert("insert into task(course_cid,longitude,latitude,type,time_limit,password,gmt_create) " +
            "values (#{course_cid},#{longitude},#{latitude},#{type},#{time_limit},#{password},#{gmt_create});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean addTask(TaskDO task);

    @Update("update task set disabled = 0 where id=#{id};")
    public Boolean setTaskEnd(Integer id);

    @Update("update select_course set totalcheck= totalcheck+1 where course_cid = #{cid};")
    public Boolean updateTotalCheck(Integer cid);

    @Insert("insert into check_log (task_id,student_sid,longitude,latitude,gmt_create) values (" +
            " #{task_id},#{query.sid},#{query.longitude},#{query.latitude},#{query.createTime} );")
    public Boolean check(Integer task_id, CheckQuery query);

    @Insert("update select_course set hascheck=hascheck+1,score=score+ #{score} where student_sid =#{sid} and course_cid=#{courseId};")
    public Boolean updateStudentCheck(Integer score, Integer sid, Integer courseId);


    @Select("select count(*) from check_log where task_id=#{taskId} and student_sid =#{sid};")
    public Integer isChecked(Integer taskId, Integer sid);

    @Select("select * from task where course_cid =#{cid};")
    public List<TaskDO> getAllTask(Integer cid);

    @Select("select * from check_log where task_id=#{tid} and student_sid=#{sid}; ")
    public CheckLog getCheckLog(Integer sid, Integer tid);
}
