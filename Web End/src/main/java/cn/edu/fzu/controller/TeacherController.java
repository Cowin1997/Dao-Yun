package cn.edu.fzu.controller;

import cn.edu.fzu.dao.TeacherMapper;
import cn.edu.fzu.entity.College;
import cn.edu.fzu.entity.Teacher;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import cn.edu.fzu.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
@Api(tags = {"教师信息接口"})
public class TeacherController {
    @Resource
    private TeacherMapper teacherMapper;

    @ResponseBody
    @RequestMapping(value = "/{tid}", method = RequestMethod.GET)
    @ApiOperation(value = "getTeacherByTid", notes = "通过教工号获取教师信息")
    public ResultModel<Teacher> getTeacherByTid(@PathVariable("tid") Integer tid) {
        Teacher teacher = this.teacherMapper.getTeacherByTid(tid);
        if (teacher != null)
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.GET_SUCCESS, teacher);
        else return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.GET_ERROR, null);
    }

    @ResponseBody
    @RequestMapping(value = "/{tid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "deleteTeacherByTid", notes = "删除教师信息")
    public ResultModel<Boolean> deleteTeacherByTid(@PathVariable(value = "tid") Integer tid) {
        Boolean aBoolean = this.teacherMapper.deleteTeacherByTid(tid);
        if (aBoolean == true)
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.DEL_SUCCESS, true);
        else return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.DEL_ERROR, false);
    }


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "addTeacher", notes = "添加教师信息")
    public ResultModel<Boolean> addTeacher(@RequestBody Teacher teacher) {
        Boolean aBoolean = this.teacherMapper.addTeacher(teacher);
        if (aBoolean == true)
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.ADD_SUCCESS, true);
        else return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.ADD_ERROR, false);
    }


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "updateTeacher", notes = "更新教师信息")
    public ResultModel<Boolean> updateTeacher(@RequestBody Teacher teacher) {
        Boolean aBoolean = this.teacherMapper.updateTeacher(teacher);
        if (aBoolean == true)
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.UPDATE_SUCCESS, true);
        else return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.UPDATE_ERROR, false);

    }


//    @ResponseBody
//    @RequestMapping(value = "",method = RequestMethod.GET)
//    public ResponseEntity getTeacherListBySchool_College_major(@RequestParam("school") Integer school,@RequestParam("college") Integer college,@RequestParam("major") Integer major){
//        HashMap res= new HashMap();
//        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
//        res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
//        List<Teacher> teacherList = this.teacherMapper.getTeacherList(school,college,major);
//        res.put(StringUtils.DATA, teacherList);
//        return ResponseEntity.status(HttpStatus.OK).body(res);
//    }


}
