package cn.edu.fzu.controller;

import cn.edu.fzu.dao.StudentMapper;
import cn.edu.fzu.dao.UserMapper;
import cn.edu.fzu.entity.Admin;
import cn.edu.fzu.entity.User;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {
    @Resource
    private UserMapper userMapper;
    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public HashMap userLogin(@RequestBody Map map){
        HashMap res= new HashMap();
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if(username!=null && password!=null){
            User user = this.userMapper.getUser(username,password);
            if(user!=null){
                res.put(StringUtils.STATUS, StringUtils.SUCCESS);
                res.put(StringUtils.DATA,user);
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
}
