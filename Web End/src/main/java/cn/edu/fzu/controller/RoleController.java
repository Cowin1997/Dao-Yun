package cn.edu.fzu.controller;

import cn.edu.fzu.dao.RoleMapper;
import cn.edu.fzu.entity.Role;
import cn.edu.fzu.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Resource
    private RoleMapper roleMapper;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getAllRole() {
        HashMap res = new HashMap();
        List<Role> roleList = this.roleMapper.getAllRole();
        res.put(StringUtils.STATUS, StringUtils.SUCCESS);
        res.put(StringUtils.MESSAGE, StringUtils.GET_SUCCESS);
        res.put(StringUtils.DATA, roleList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
