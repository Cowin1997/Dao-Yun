package cn.edu.fzu.controller;

import cn.edu.fzu.dao.SchoolMapper;
import cn.edu.fzu.entity.Admin;
import cn.edu.fzu.entity.School;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = {"/school"})
public class SchoolController {
    @Resource
    private SchoolMapper schoolMapper;

    @RequestMapping(value = {""},method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getSchools(){
        HashMap map = new HashMap();
        List<School> schoolList = this.schoolMapper.getSchools();
        map.put(StringUtils.STATUS,StringUtils.SUCCESS);
        map.put(StringUtils.DATA,schoolList);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @RequestMapping(value = "{sch_code}",method = RequestMethod.GET)
    public ResponseEntity getSchoolBySchoolCode(@PathVariable(value = "sch_code",required = true)Integer code){
        HashMap res = new HashMap();
        School school = this.schoolMapper.getSchoolBySchoolCode(code);
        if(school!=null){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.DATA,school);
            res.put(StringUtils.MESSAGE,StringUtils.GET_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.ERROR,StringUtils.USER_DO_NOT_EXIST);
            res.put(StringUtils.MESSAGE,StringUtils.GET_FAIL);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

}
