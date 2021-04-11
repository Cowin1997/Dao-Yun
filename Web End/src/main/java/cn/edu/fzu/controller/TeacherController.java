package cn.edu.fzu.controller;

import cn.edu.fzu.dao.TeacherMapper;
import cn.edu.fzu.entity.Teacher;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping(value = "teacher")
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

}
