package cn.edu.fzu.controller;

import cn.edu.fzu.dao.MenuMapper;
import cn.edu.fzu.dao.PermissionMapper;
import cn.edu.fzu.entity.Permission;
import cn.edu.fzu.entity.User;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import cn.edu.fzu.utils.StringUtils;
import cn.edu.fzu.entity.Menu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private MenuMapper menuMapper;
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultModel<List<Integer>> getPermissionByUri(@RequestParam(value = "uri")String uri, @RequestParam("type") Integer type) {
        List<Permission> permissionList = this.permissionMapper.getPermissionByUriAndRoleId(type + "|", uri);
        List<Integer> codeList = new ArrayList<>();
        for (Permission p : permissionList) {
            codeList.add(p.getCode());
        }
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.GET_SUCCESS, codeList);
    }


    @ResponseBody
    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    public ResponseEntity getPermissionByMenuIdAndRoleid(@RequestParam(value = "menuid",required = false)Integer menuid, @RequestParam(value = "roleid",required = false) Integer roleid,HttpServletRequest request){
        System.out.println(menuid);
        System.out.println(roleid);
        HashMap res = new HashMap();
        List list = new ArrayList();
        if(menuid == null && roleid== null){
            List<Menu> menuList = this.menuMapper.getAllMenusOrderByIdAsc();
            for(int i=0;i<menuList.size();i++){
                HashMap hm = new HashMap();
                hm.put("name",menuList.get(i).getTitle());
                hm.put("id",menuList.get(i).getId());
//                hm.put("disabled",true);
                List<Permission> permissionList = this.permissionMapper.getPermissionByMenuId(menuList.get(i).getId());
                if(permissionList.size() > 0) hm.put("subs",permissionList);
                list.add(hm);
            }
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.DATA,list);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else if(menuid != null && roleid!=null){
            List<Menu> menuList = this.menuMapper.getMenus(roleid+"|");
            for(int i=0;i<menuList.size();i++){
                HashMap hm = new HashMap();
                hm.put("name",menuList.get(i).getTitle());
                hm.put("id",menuList.get(i).getId());
//                hm.put("disabled",true);
                List<Permission> permissionList = this.permissionMapper.getPermissionByMenuIdAndRoleId(menuList.get(i).getId(),roleid+"|");
                if(permissionList.size() > 0) hm.put("subs",permissionList);
                list.add(hm);
            }
            res.put(StringUtils.STATUS,StringUtils.SUCCESS);
            res.put(StringUtils.DATA,list);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else if(menuid == null && roleid!=null){
                res.put(StringUtils.STATUS,StringUtils.SUCCESS);
                list = this.permissionMapper.getPermissionIdByRoleId(roleid+"|");
                res.put(StringUtils.DATA,list);
                return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }


    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity setPermission(@RequestBody HashMap map){
        HashMap res = new HashMap();
        List<Integer> setList = (List<Integer>) map.get("setList");
        List<Integer> unSetList = (List<Integer>) map.get("unSetList");
        Integer roleid = (Integer) map.get("roleid");
        System.out.println("roleid:"+roleid);
        System.out.println("setList:"+setList);
        System.out.println("unSetList:"+unSetList);
        for (Integer e:setList) {
           Permission p = this.permissionMapper.getPermissionById(e);
           if(p!=null){
               String currentRoleId = p.getRoleid();
               if(currentRoleId==null) currentRoleId="";
               if(currentRoleId.contains(roleid+"|")) continue;
               else {
                   currentRoleId += (roleid+"|");
                   this.permissionMapper.setPermission(e,currentRoleId);
               }
           }
        }

        for (Integer e:unSetList
        ) {
            Permission p = this.permissionMapper.getPermissionById(e);
            if(p!=null){
                String currentRoleId = p.getRoleid();
                if(currentRoleId==null) currentRoleId="";
                if(!currentRoleId.contains(roleid+"|")) continue;
                else {
                    currentRoleId = currentRoleId.replace(roleid+"|","");
                    this.permissionMapper.setPermission(e,currentRoleId);
                }
            }
        }
        res.put(StringUtils.STATUS,StringUtils.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }
}


