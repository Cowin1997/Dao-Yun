package cn.edu.fzu.controller;

import cn.edu.fzu.dao.StudentMapper;
import cn.edu.fzu.entity.Student;
import cn.edu.fzu.utils.StringUtils;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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
        List<Student> getres = this.studentMapper.getStudent(st_id);
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


    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity getStudentByClassId(@RequestParam(value = "classid",required = false) Integer classid,@RequestParam(value = "page",required = false) Integer page,@RequestParam(value = "size",required = false) Integer size){

        HashMap res = new HashMap();
        Integer totalStudent = this.studentMapper.getStudentTotalNumber();
        if(classid!=null && page!=null &&size!=null&& Math.ceil(Double.valueOf(totalStudent) /size) >= page&&page>=1&&size>=1) {
                Integer from = (page - 1)*size;
                System.out.println("-------------------------------------------------");
                System.out.println(from+","+size+","+classid);
                System.out.println("-------------------------------------------------");
                List<Student> studentList = this.studentMapper.getStudentByPage(from,size,classid);
                res.put(StringUtils.STATUS, StringUtils.SUCCESS);
                res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
                res.put(StringUtils.DATA, studentList);
                res.put("total", totalStudent);
                res.put("page", Math.ceil(Double.valueOf(totalStudent)/size));
                res.put("size", studentList.size());
                return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS, StringUtils.FAIL);
            res.put(StringUtils.MESSAGE, StringUtils.PARAM_ERROR);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/{sid}",method = RequestMethod.DELETE)
    public ResponseEntity delStudentBySid(@PathVariable(value = "sid",required = true) Integer sid ){
        HashMap res = new HashMap();
        Boolean delResult = this.studentMapper.delStudentBySid(sid);
        if(delResult==true){
            res.put(StringUtils.STATUS, StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE, StringUtils.DEL_SUCCESS);
        }else{
            res.put(StringUtils.STATUS, StringUtils.FAIL);
            res.put(StringUtils.MESSAGE, StringUtils.DEL_FAIL);
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResponseEntity updateStudentBySid(@RequestBody Student student){
        HashMap res = new HashMap();
        Boolean delResult = this.studentMapper.updateStudent(student);
        if(delResult==true){
            res.put(StringUtils.STATUS, StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE, StringUtils.UPDATE_SUCCESS);
        }else{
            res.put(StringUtils.STATUS, StringUtils.FAIL);
            res.put(StringUtils.MESSAGE, StringUtils.UPDATE_FAIL);
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }










}
