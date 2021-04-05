package cn.edu.fzu.controller;

import cn.edu.fzu.dao.AdminMapper;
import cn.edu.fzu.entity.Admin;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {
    @Resource
    private AdminMapper adminMapper;

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public Map adminLogin(@RequestBody Map map){
        HashMap res= new HashMap();
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if(username!=null && password!=null){
            Admin admin = this.adminMapper.getAdmin(username,password);
            if(admin!=null){
                res.put(StringUtils.STATUS, StringUtils.SUCCESS);
                res.put(StringUtils.DATA,admin);
                return res;
            }else {
                res.put(StringUtils.STATUS, StringUtils.FAIL);
                res.put(StringUtils.ERROR, StringUtils.ERROR_LOGIN_USERNAME_OR_PASSWORD);
                return res;
            }
        }else {
            res.put(StringUtils.STATUS, StringUtils.FAIL);
            res.put(StringUtils.ERROR, StringUtils.REQUIRED_USERNAME_OR_PASSWORD);
            return res;
        }
    }

    @RequestMapping(value={""},method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAdmins(){
        HashMap res = new HashMap();
        List<Admin> admins = this.adminMapper.getAdmins();
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        res.put(StringUtils.DATA,admins);
        res.put(StringUtils.MESSAGE,StringUtils.GET_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ResponseBody
    @RequestMapping(value={"/{id}"},method = RequestMethod.GET)
    public ResponseEntity getAdminById(@PathVariable(value = "id",required = true)Integer id) {
        HashMap res = new HashMap();
        Admin admin = this.adminMapper.getAdminById(id);
        if(admin!=null){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.DATA,admin);
            res.put(StringUtils.MESSAGE,StringUtils.GET_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.ERROR,StringUtils.USER_DO_NOT_EXIST);
            res.put(StringUtils.MESSAGE,StringUtils.GET_FAIL);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }


    @ResponseBody
    @RequestMapping(value={""},method = RequestMethod.PUT)
    public ResponseEntity addAdmin(@RequestBody(required = true) Admin admin){
        HashMap res = new HashMap();
        admin.setAd_createtime(new Date());
        Boolean addRes = this.adminMapper.addAdmin(admin);
        if(addRes==true){
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
    @RequestMapping(value={"/{id}"},method = RequestMethod.DELETE)
    public ResponseEntity delAdminById(@PathVariable("id")Integer id){
        HashMap res = new HashMap();
        Boolean delRes = this.adminMapper.delAdminById(id);
        if(delRes==true){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE,StringUtils.DEL_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.DEL_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }


    @ResponseBody
    @RequestMapping(value={""},method = RequestMethod.POST)
    public ResponseEntity updateAdmin(@RequestBody(required = true) Admin admin){
        HashMap res = new HashMap();
        Boolean upRes = this.adminMapper.updateAdmin(admin);
        if(upRes==true){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }
    @ResponseBody
    @RequestMapping(value={"/password-reset"},method = RequestMethod.POST)
    public ResponseEntity passwordRest(@RequestBody Admin admin){
        HashMap res = new HashMap();
        Boolean resRes = this.adminMapper.passwordRest(admin);
        if(resRes==true){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    @ResponseBody
    @RequestMapping(value={"/forbid/{id}"},method = RequestMethod.POST)
    public ResponseEntity forbidAccount(@PathVariable("id") Integer id){
        HashMap res = new HashMap();
        Boolean forRes = this.adminMapper.forbidAccount(id);
        if(forRes==true){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }
    @ResponseBody
    @RequestMapping(value={"/unforbid/{id}"},method = RequestMethod.POST)
    public ResponseEntity unforbidAccount(@PathVariable("id") Integer id){
        HashMap res = new HashMap();
        Boolean forRes = this.adminMapper.unforbidAccount(id);
        if(forRes==true){
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            res.put(StringUtils.STATUS,StringUtils.FAIL);
            res.put(StringUtils.MESSAGE,StringUtils.UPDATE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }


}
