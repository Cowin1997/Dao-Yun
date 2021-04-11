package cn.edu.fzu.controller;

import cn.edu.fzu.dao.UserMapper;
import cn.edu.fzu.entity.User;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {
    @Resource
    private UserMapper userMapper;
    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public HashMap userLogin(@RequestBody Map map, HttpServletRequest request){
        HashMap res= new HashMap();
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if(username!=null && password!=null){
            User user = this.userMapper.getUser(username,password);
            if(user!=null){
                res.put(StringUtils.STATUS, StringUtils.SUCCESS);
                res.put(StringUtils.DATA,user);
                HttpSession session = request.getSession(true);
                System.out.println(user);
                session.setAttribute("user",user);
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


    @ResponseBody
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ResponseEntity getAllAdmin(){
        HashMap res= new HashMap();
        List<User> userList = this.userMapper.getAllAdmin();
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.DATA,userList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @ResponseBody
    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    public ResponseEntity addAdmin(@RequestBody User user){
        HashMap res= new HashMap();
        user.setUs_registtime(new Date());
        System.out.println(user);
        Boolean aBoolean = this.userMapper.addUser(user);
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.DATA,aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ResponseBody
    @RequestMapping(value = "/admin",method = RequestMethod.PUT)
    public ResponseEntity updateAdmin(@RequestBody User user){
        HashMap res= new HashMap();
        System.out.println(user);
        Boolean aBoolean = this.userMapper.updateUser(user);
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.DATA,aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @ResponseBody
    @RequestMapping(value = "/admin",method = RequestMethod.DELETE)
    public ResponseEntity delAdmin(@RequestParam("userId")Integer id){
        HashMap res= new HashMap();

        Boolean aBoolean = this.userMapper.delUser(id);
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.DATA,aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public ResponseEntity changePassword(@PathVariable("id") Integer id,
                                         @RequestBody HashMap map){
        HashMap res= new HashMap();

        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");
        Boolean aBoolean = this.userMapper.userPasswordResetById(newPassword,id);
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.DATA,aBoolean);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
