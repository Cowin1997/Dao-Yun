package cn.edu.fzu.controller;

import cn.edu.fzu.dao.MajorMapper;
import cn.edu.fzu.entity.College;
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
@RequestMapping(value = {"/major"})
public class MajorController {
    @Resource
    private MajorMapper majorMapper;
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity getMajorBySchCodeAndCOLID(@RequestParam("sch_code") Integer sch_code,@RequestParam("col_id")Integer col_id) {
        HashMap res = new HashMap();
        List<Major> majorList = this.majorMapper.getMajorBySchooCodeAndCollegeId(sch_code,col_id);
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,majorList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
