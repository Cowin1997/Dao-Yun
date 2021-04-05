package cn.edu.fzu.controller;

import cn.edu.fzu.dao.StudentMapper;
import cn.edu.fzu.entity.Student;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping(value = {"/student"})
public class StudentController {
    @Resource
    private StudentMapper studentMapper;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity addStudent(@RequestBody Student student){
        HashMap res = new HashMap();
        Boolean addres = this.studentMapper.addStudent(student);
        if(addres==true){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE,StringUtils.ADD_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.ADD_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/{st_id}",method = RequestMethod.GET)
    public ResponseEntity getStudent(@PathVariable("st_id") String st_id){
        HashMap res = new HashMap();
        Student getres = this.studentMapper.getStudent(st_id);
        if(getres!=null){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.DATA,getres);
            res.put(StringUtils.MESSAGE,StringUtils.GET_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.GET_FAIL);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

}
