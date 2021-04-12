package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CheckLogMapper;
import cn.edu.fzu.dao.CheckTaskMapper;
import cn.edu.fzu.dao.ConfigDataMapper;
import cn.edu.fzu.entity.CheckLog;
import cn.edu.fzu.entity.CheckTask;
import cn.edu.fzu.entity.ConfigData;
import cn.edu.fzu.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/check")
public class CheckController {
    @Resource
    private CheckLogMapper checkLogMapper;
    @Resource
    private CheckTaskMapper checkTaskMapper;
    @Resource
    private ConfigDataMapper dataMapper;


    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity CheckTask(@RequestBody HashMap map) {
        HashMap res = new HashMap();
        String studentId = (String) map.get("studentId");
        Integer taskId = (Integer) map.get("taskId");
        Integer classId = (Integer) map.get("classId");
        String location = (String) map.get("location");
        String password = (String) map.get("password");
        if(studentId!=null && taskId!=null && classId!=null){
            ConfigData data = dataMapper.getByCode(10000);
           Boolean aBoolean = this.checkLogMapper.check(taskId,studentId,classId,location,Integer.valueOf(data.getValue()),new Date());
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.DATA,aBoolean);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

    }



    @RequestMapping(value = "/task",method = RequestMethod.POST)
    public ResponseEntity createTask(@RequestBody CheckTask task){
        HashMap res = new HashMap();
        Boolean aBoolean = this.checkTaskMapper.insert(task);
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @RequestMapping(value = "/task",method = RequestMethod.GET)
    public ResponseEntity getTask(@RequestParam(value = "sid") String studentId){
        HashMap res = new HashMap();
        String sid = studentId;
        System.out.println(sid);
        CheckTask uncheckTask = this.checkTaskMapper.getUncheckTask();
        if(uncheckTask!=null) {
            Integer taskId = uncheckTask.getId();
            Integer classId = uncheckTask.getClass_id();
            CheckLog log = this.checkLogMapper.isCheck(sid, taskId, classId);
            if(log==null) uncheckTask.setCheck(false);

            res.put(StringUtils.STATUS, StringUtils.SUCCESS);
            res.put(StringUtils.DATA, uncheckTask);
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @RequestMapping(value = "/task-list",method = RequestMethod.GET)
    public ResponseEntity getTaskList(@RequestParam(value = "sid") String studentId,@RequestParam(value = "cid") Integer classid){
        HashMap res = new HashMap();
        String sid = studentId;
        System.out.println(sid);
        List<CheckTask> checkTaskList = this.checkTaskMapper.getTaskList(classid);
        if(checkTaskList!=null) {
            for (CheckTask task:checkTaskList) {
                if(this.checkLogMapper.isCheck(studentId,task.getId(),classid)==null){
                    task.setCheck(false);
                }else{
                    task.setCheck(true);
                    task.setLog(this.checkLogMapper.getCheckLogsBySid_Cid_Taskid(studentId,task.getClass_id(), task.getId()));
                }
            }
            res.put(StringUtils.STATUS, StringUtils.SUCCESS);
            res.put(StringUtils.DATA, checkTaskList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }




}
