package cn.edu.fzu.daoyun.service;

import cn.edu.fzu.daoyun.entity.TeacherDO;
import cn.edu.fzu.daoyun.query.AddTeacherQuery;
import io.swagger.models.auth.In;

public interface TeacherService {
    /*
        根据 tid 获取教师信息
     */
    public TeacherDO getTeacherByTid(Integer tid);
    /*
       添加教师信息
    */
    public Boolean addTeacher(AddTeacherQuery query);

    /*
    根据 tid 删除教师信息
    */
    public Boolean delTeacherByTid(Integer tid);

    /*
    根据 tid 删除教师信息
    */
    public Boolean updTeacherByTid(TeacherDO teacher);

}
