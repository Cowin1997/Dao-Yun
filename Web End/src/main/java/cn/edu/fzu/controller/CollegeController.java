package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CollegeMapper;
import cn.edu.fzu.entity.College;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = {"/college"})
public class CollegeController {
    @Resource
    private CollegeMapper collegeMapper;


    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity getCollegeBySchoolCode(@RequestParam("sch_code") Integer sch_code){
        HashMap res = new HashMap();
        List<College> collegeList = this.collegeMapper.getCollegeBySchoolCode(sch_code);
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,collegeList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }



    @ResponseBody
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity getCollegeByCollegeId(@PathVariable(value = "id",required = true) Integer id){
        HashMap res = new HashMap();
        List<College> collegeList = this.collegeMapper.getCollegeByCollegeId(id);
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,collegeList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
