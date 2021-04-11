package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CheckLogMapper;
import cn.edu.fzu.entity.CheckLog;
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
@RequestMapping(value = "/check-log")
public class CheckLogController {
    @Resource
    private CheckLogMapper checkLogMapper;


    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCheckLogs(@RequestParam(value = "sid",required = false) Integer sid,@RequestParam(value = "cid",required = false) Integer cid){
        HashMap res = new HashMap();
        List<CheckLog> checkLogList = this.checkLogMapper.getCheckLogsBySidAndCid(sid,cid);
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,checkLogList);
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }
}
