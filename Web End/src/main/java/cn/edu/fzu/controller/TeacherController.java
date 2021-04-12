package cn.edu.fzu.controller;

import cn.edu.fzu.dao.TeacherMapper;
import cn.edu.fzu.entity.Teacher;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Resource
    private TeacherMapper teacherMapper;

    @ResponseBody
    @RequestMapping("/user/{id}")
    public ResponseEntity getTeacherByUserId(@PathVariable("id") Integer id){
        HashMap res= new HashMap();
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
        Teacher teacher = this.teacherMapper.getTeacherByUseId(id);
        res.put(StringUtils.DATA, teacher);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }



    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity getTeacherListBySchool_College_major(@RequestParam("school") Integer school,@RequestParam("college") Integer college,@RequestParam("major") Integer major){
        HashMap res= new HashMap();
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
        List<Teacher> teacherList = this.teacherMapper.getTeacherList(school,college,major);
        res.put(StringUtils.DATA, teacherList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteTeacher(@PathVariable(value = "id") Integer id){
        HashMap res= new HashMap();
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
        Boolean aBoolean = this.teacherMapper.delete(id);
        res.put(StringUtils.DATA, aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity updateTeacher(@RequestBody Teacher teacher){
        HashMap res= new HashMap();
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
        Boolean aBoolean = this.teacherMapper.update(teacher);
        res.put(StringUtils.DATA, aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResponseEntity putTeacher(@RequestBody Teacher teacher){
        HashMap res= new HashMap();
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
        Boolean aBoolean = this.teacherMapper.insert(teacher);
        res.put(StringUtils.DATA, aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
