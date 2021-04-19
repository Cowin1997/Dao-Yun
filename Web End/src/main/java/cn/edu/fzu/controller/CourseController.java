package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CourseMapper;
import cn.edu.fzu.dao.MajorMapper;
import cn.edu.fzu.entity.Course;
import cn.edu.fzu.entity.Major;
import cn.edu.fzu.entity.School;
import cn.edu.fzu.entity.selectCourse;
import cn.edu.fzu.param.CourseReq;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import cn.edu.fzu.utils.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
@RequestMapping(value = {"/course"})
@Api(tags={"班课信息接口"})
public class CourseController {
    @Resource
    private CourseMapper courseMapper;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ApiOperation(value = "getCourseByTid",notes="根据教师工号,获取该教师的所有班课列表")
    public ResultModel<List<Course>> getCourseByTid(@RequestParam("tid") String tid) {
        List<Course> courseList = this.courseMapper.getCourseByTid(tid);
        return new ResultModel<List<Course>>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,courseList);
    }

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ApiOperation(value = "addCourse",notes="添加班课")
    public ResultModel<Boolean> addCourse(@RequestBody CourseReq course) {
        Course isExist = this.courseMapper.getCourseByCid(course.getCs_id());
        if(isExist!=null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.CLASS_IS_EXIST,false);
        else{
            Boolean aBoolean = this.courseMapper.addCourse(course);
            if(aBoolean==true) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.ADD_SUCCESS,true);
        }
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.ADD_ERROR,false);
    }


    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ApiOperation(value = "updateCourse",notes="添加班课,只允许修改班课名,上课地点,上课时间,是否禁止加入班课,班课说明。更新按照班课编号。")
    public ResultModel<Boolean> updateCourse(@RequestBody CourseReq course) {
        Boolean aBoolean = this.courseMapper.updateCourse(course);
        if(aBoolean==true) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.UPDATE_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,false);
    }



    @ResponseBody
    @RequestMapping(value = "/{cid}",method = RequestMethod.DELETE)
    @ApiOperation(value = "deleteCourse",notes="根据班课ID删除班课")
    public ResultModel<Boolean> deleteCourse(@PathVariable("cid") String cid) {
        Boolean aBoolean = this.courseMapper.deleteCourse(cid);
        if(aBoolean==true) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.DEL_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.DEL_ERROR,false);

    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "getCourseBySch_Col_Maj",notes="获取某学校,某学院,某专业下的所有班课列表")
    public ResultModel<List<Course>> getCourseBySch_Col_Maj(@RequestParam("sch") Integer sch, @RequestParam("col")Integer col,@RequestParam("maj")Integer maj) {
        HashMap res = new HashMap();
        List<Course> courseList = this.courseMapper.getCourseBySch_Col_Maj(sch,col,maj);
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,courseList);
    }



    @ResponseBody
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public ResultModel<Boolean> selectCourse(@RequestBody selectCourse selectCourse){
        Boolean isExist = this.courseMapper.isSelect(selectCourse.getSt_id(), selectCourse.getCs_id());
        if(isExist) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SELECT_COURSE_EXIST,false);
        else{
            Boolean selectRes = this.courseMapper.Select(selectCourse.getSt_id(), selectCourse.getCs_id());
            if(selectRes) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.SELECT_COURSE_SUCCESS,true);
            return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SELECT_COURSE_ERROR,false);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/un-Select",method = RequestMethod.DELETE)
    public ResultModel<Boolean> unSelectCourse(@RequestBody selectCourse unSelectCourse){
      Boolean aBoolean = this.courseMapper.unSelect(unSelectCourse.getSt_id(),unSelectCourse.getCs_id());
      if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.DEL_SUCCESS,true);
      return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.DEL_ERROR,false);
    }



}
