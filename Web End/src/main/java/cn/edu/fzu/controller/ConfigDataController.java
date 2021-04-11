package cn.edu.fzu.controller;

import cn.edu.fzu.dao.ConfigDataMapper;
import cn.edu.fzu.entity.College;
import cn.edu.fzu.entity.ConfigData;
import cn.edu.fzu.utils.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/data-config")
public class ConfigDataController {
    @Resource
    private ConfigDataMapper configDataMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getConfigDatas(){
        HashMap res = new HashMap();
        List<ConfigData> configDataList = this.configDataMapper.getConfigDatas();
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,configDataList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }



    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateConfigData(@RequestBody ConfigData data){
        HashMap res = new HashMap();
        System.out.println(data);
        Boolean aBoolean = this.configDataMapper.updateConfigData(data);

        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.DATA,aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }


    @RequestMapping(value = "",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity addConfigData(@RequestBody ConfigData data){
        HashMap res = new HashMap();
        System.out.println(data);
        Boolean  addResult = this.configDataMapper.addConfigData(data);
        if(addResult==true){
            res.put(StringUtils.STATUS, StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE,StringUtils.ADD_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS, StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.ADD_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delConfigData(@PathVariable("id") Integer id){
        HashMap res = new HashMap();
        Boolean  aBoolean = this.configDataMapper.delete(id);
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

