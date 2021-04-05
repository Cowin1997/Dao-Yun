package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CourseMapper;
import cn.edu.fzu.dao.MajorMapper;
import cn.edu.fzu.entity.Course;
import cn.edu.fzu.entity.Major;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = {"/course"})
public class CourseController {
    @Resource
    private CourseMapper courseMapper;
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity getMajorBySchCodeAndCOLID(@RequestParam("sch_code") Integer sch_code, @RequestParam("col_id")Integer col_id,@RequestParam("maj_id")Integer maj_id) {
        HashMap res = new HashMap();
        List<Course> courseList = this.courseMapper.getCourseBySchCode_ColId_MajorId(sch_code,col_id,maj_id);
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,courseList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
