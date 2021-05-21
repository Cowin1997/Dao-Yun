package cn.edu.fzu.daoyun.controller;

import cn.edu.fzu.daoyun.annotation.AnonymousGetMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPostMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPutMapping;
import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.base.Result;
import cn.edu.fzu.daoyun.constant.ResultCodeEnum;
import cn.edu.fzu.daoyun.dto.CourseDTO;
import cn.edu.fzu.daoyun.dto.CourseDTO2;
import cn.edu.fzu.daoyun.dto.StudentCheckLogDTO;
import cn.edu.fzu.daoyun.entity.CourseDO;
import cn.edu.fzu.daoyun.entity.StudentDO;
import cn.edu.fzu.daoyun.entity.TeacherDO;
import cn.edu.fzu.daoyun.exception.BadRequestException;
import cn.edu.fzu.daoyun.mapper.CourseMapper;
import cn.edu.fzu.daoyun.query.UpdateStatusQuery;
import cn.edu.fzu.daoyun.service.CourseService;
import cn.edu.fzu.daoyun.service.TeacherService;
import cn.edu.fzu.daoyun.service.impl.CourseServiceImpl;
import cn.edu.fzu.daoyun.service.impl.TeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Api(tags={"班课管理接口"})
@RequestMapping("/api/course")
public class CourseController {
    @Resource
    private CourseServiceImpl courseService;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherServiceImpl teacherService;
    @AnonymousPostMapping(value = "")
    @ApiOperation(value = "addCourse",notes = "添加班课")
    public Result<Boolean> addCourse(@RequestBody CourseDO course){
        Boolean isSuccess = this.courseService.addCourse(course);
        if(isSuccess==true){
            return Result.success(ResultCodeEnum.ADD_SUCCESS);
        }
        return Result.failure(ResultCodeEnum.ADD_FAILURE);
    }


    @AnonymousPutMapping(value = "")
    @ApiOperation(value = "updateCourse",notes = "更新班课信息")
    public Result updateCourse(@RequestBody CourseDO course){
        TeacherDO teacher = this.teacherService.getTeacherByTid(course.getTeacher_tid());
        if(teacher==null) return Result.failure(ResultCodeEnum.TEACHER_UNEXIST);
        Boolean isSuccess = this.courseService.updateCourse(course);
        if(isSuccess==true) {
            return Result.success(ResultCodeEnum.UPD_SUCCESS);
        }
        return Result.failure(ResultCodeEnum.UPD_FAILURE);
    }

    @AnonymousGetMapping(value = "")
    @ApiOperation(value = "getCourse",notes = "根据班课Id获取班课信息")
    public Result getCourse(@RequestParam("cid") Integer cid) {
        CourseDO course = this.courseService.getCourseByCid(cid);
        if(course!=null){
            return Result.success(ResultCodeEnum.SUCCESS,course);
        }
        return Result.failure(ResultCodeEnum.COURSE_UNEXIST);
    }
    @AnonymousGetMapping(value = "/s")
    @ApiOperation(value = "getCourseByStudent",notes = "根据学生ID获取该学生的所有课程")
    public Result getCourseByStudent(@RequestParam("sid") Integer sid) {
        List<CourseDTO> courseList = this.courseService.getCourseBySid(sid);
         return Result.success(ResultCodeEnum.SUCCESS,courseList);

    }

    @AnonymousGetMapping(value = "/t")
    @ApiOperation(value = "getCourseByTeacher",notes = "根据教师ID获取该学生的所有课程")
    public Result getCourseByTeacher(@RequestParam("tid") Integer tid) {
        List<CourseDO> coueseList = this.courseService.getCourseByTid(tid);
        return Result.success(ResultCodeEnum.SUCCESS,coueseList);
    }


    @AnonymousGetMapping(value = "/c")
    @ApiOperation(value = "getStudentListByClassid",notes = "根据班课ID获取所有学生的信息")
    public Result getStudentListByClassid(@RequestParam("cid") Integer cid) {
        List<StudentCheckLogDTO> studentByClassId = this.courseMapper.getStudentByClassId(cid);
        return Result.success(ResultCodeEnum.SUCCESS,studentByClassId);
    }


    @AnonymousGetMapping(value = "/select")
    @ApiOperation(value = "selectCourse",notes = "学生选择班课")
    public Result selectCourse(@RequestParam("cid") Integer cid,@RequestParam("sid") Integer sid) {
        Boolean aBoolean = this.courseMapper.selectCourse(cid, sid);
        System.out.println(aBoolean);
        if(aBoolean) return Result.success(ResultCodeEnum.ADD_SUCCESS);
        return Result.failure(ResultCodeEnum.ADD_FAILURE);
    }

    @AnonymousGetMapping(value = "/unselect")
    @ApiOperation(value = "unSelectCourse",notes = "学生退选班课")
    public Result unSelectCourse(@RequestParam("cid") Integer cid,@RequestParam("sid") Integer sid) {
        Boolean aBoolean = this.courseMapper.deleteCourse(cid, sid);
        if(aBoolean) return Result.success(ResultCodeEnum.DEL_SUCCESS);
        return Result.failure(ResultCodeEnum.DEL_FAILURE);
    }

    @AnonymousPostMapping(value = "/status")
    @ApiOperation(value = "updateStatus",notes = "更新状态:当前班课是否可加入")
    public Result updateStatus(@RequestBody UpdateStatusQuery query) {
        Boolean aBoolean = this.courseMapper.updateStatus(query.getCid(), query.getEnabled());
        if(aBoolean) return Result.success(ResultCodeEnum.UPD_SUCCESS);
        return Result.failure(ResultCodeEnum.DEL_FAILURE);
    }


    @AnonymousGetMapping(value = "/list")
    @ApiOperation(value = "getCourseList",notes = "获取课表列表")
    public Result<Page<CourseDTO2>> getCourseList(@RequestParam(value = "sch") Integer sch,
                                               @RequestParam(value = "col") Integer col,
                                               @RequestParam(value = "maj") Integer maj,
                                               @RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "size") Integer size){

        if(sch>0 && col>0 && maj>0 && page >0 && size >0){
            Page<CourseDTO2> pageData = this.courseService.getCourseListByOrg(sch, col, maj, page, size);
            return Result.success(ResultCodeEnum.SUCCESS,pageData);
        }
        throw new BadRequestException("请求参数错误");
    }


}












