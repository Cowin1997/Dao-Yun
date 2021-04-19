package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CheckLogMapper;
import cn.edu.fzu.dao.CheckTaskMapper;
import cn.edu.fzu.dao.ConfigDataMapper;
import cn.edu.fzu.dao.CourseMapper;
import cn.edu.fzu.entity.*;
import cn.edu.fzu.param.CheckRes;
import cn.edu.fzu.param.LoginRes;
import cn.edu.fzu.param.checkReq;
import cn.edu.fzu.param.logRes;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import cn.edu.fzu.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
@Slf4j
@Controller
@RequestMapping(value = "/check")
@Api(tags={"签到任务发起和签到接口"})
public class CheckController {
    @Resource
    private CheckTaskMapper checkTaskMapper;
    @Resource
    private CheckLogMapper checkLogMapper;
    @Resource
    private CourseMapper courseMapper;
    @ResponseBody
    @RequestMapping(value = "/task",method = RequestMethod.POST)
    @ApiOperation(value = "createTask",notes = "教师创建签到任务")
    public ResultModel<Integer> createTask(@RequestBody CheckTask task) {
        task.setStatus(true);
        task.setCreate_time(new Date());
        Integer id = this.checkTaskMapper.addTask(task);
        if(id!=0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    checkTaskMapper.setTaskEnd(task.getId());
                }
            },task.getTime_limit()*1000);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.CREATE_SUCCESS, task.getId());
        }
        return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.CREATE_ERROR, null);
    }


    @ResponseBody
    @RequestMapping(value = "/task-list",method = RequestMethod.GET)
    @ApiOperation(value = "getTaskListByClassId",notes = "根据班课号,获取班课的所有签到任务")
    public ResultModel<List<CheckTask>> getTaskListByClassId(@RequestParam(value = "cid") String cid){
        List<CheckTask> checkTaskList = this.checkTaskMapper.getTaskListByClassId(cid);
        return new ResultModel<List<CheckTask>>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,checkTaskList);
    }

    @ResponseBody
    @RequestMapping(value = "/task",method = RequestMethod.GET)
    @ApiOperation(value = "getUncheckTask",notes = "根据班课号获取最新的未签到的任务")
    public ResultModel<CheckTask> getUncheckTask(@RequestParam(value = "cid") String cid){
        CheckTask checkTask = this.checkTaskMapper.getUncheckTask(cid);
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,checkTask);
    }


    @ResponseBody
    @RequestMapping(value = "/task/{tid}",method = RequestMethod.GET)
    @ApiOperation(value = "getCheckResultByTid",notes = "根据签到任务Id,获取学生签到结果。")
    public ResultModel<List<CheckRes>> getCheckResultByTid(@RequestParam(value = "tid") Integer tid){
        List<CheckRes> checkResList = new ArrayList<>();
        CheckTask checkTask = this.checkTaskMapper.getTaskById(tid);
        List<Student> studentList = this.courseMapper.getStudentsByClassId(checkTask.getClass_id());
        for (Student s:studentList) {
            CheckLog log = this.checkLogMapper.getLog(tid,s.getSt_id());
            checkResList.add(new CheckRes(s,log));
        }
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,checkResList);
    }



    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ApiOperation(value = "check",notes = "签到")
    public ResultModel<Boolean> check(checkReq req){
        CheckLog log = this.checkLogMapper.getLog(req.getCh_taskid(),req.getCh_sid());
        if(log!=null)  return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.CHECK_ALREADY,false);
        else if (this.checkTaskMapper.isEnd(req.getCh_taskid())==true) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.CHECK_HAD_END,false);
        else{
            req.setCh_checktime(new Date());
            req.setCh_checkscore(2); //应该从系统参数读取每次签到的经验值
            Boolean aBoolean =  this.checkLogMapper.check(req);
            if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.CHECK_SUCCESS,true);
        }
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.CHECK_ERROR,false);
    }

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ApiOperation(value = "reCheck",notes = "补签")
    public ResultModel<Boolean> reCheck(checkReq req){
        CheckLog log = this.checkLogMapper.getLog(req.getCh_taskid(),req.getCh_sid());
        if(log!=null)  return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.CHECK_ALREADY,false);
        else{
            req.setCh_checktime(new Date());
            req.setCh_checkscore(2); //应该从系统参数读取每次签到的经验值
            Boolean aBoolean =  this.checkLogMapper.check(req);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.CHECK_SUCCESS,true);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/sum",method = RequestMethod.GET)
    @ApiOperation(value = "getSum",notes = "根据学生学号和班课ID获取该课程签到总分")
    public ResultModel<Integer> getSum(@ApiParam(value = "学生学号") @RequestParam("sid") String sid, @RequestParam("cid") String cid){
         Integer sum =  this.checkLogMapper.getSum(sid,cid);
         if(sum!=null) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,sum);
         else return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.GET_ERROR,sum);
    }


    @ResponseBody
    @RequestMapping(value = "/log",method = RequestMethod.GET)
    @ApiOperation(value = "getLog",notes = "根据学生学号和班课ID获取该课程签到记录")
    public ResultModel<List<logRes>> getLog(@ApiParam(value = "学生学号") @RequestParam("sid") String sid, @RequestParam("cid") String cid){
        List<logRes> logResList= new ArrayList<>();
        List<CheckTask> checkTaskList =  this.checkTaskMapper.getTaskListByClassId(cid);
        for (CheckTask task:checkTaskList){
            CheckLog log = this.checkLogMapper.getLog(task.getId(),sid);
            logResList.add(new logRes(task,log));
        }
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,logResList);
    }


}
