package cn.edu.fzu.daoyun.controller;


import cn.edu.fzu.daoyun.annotation.AnonymousGetMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPostMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPutMapping;
import cn.edu.fzu.daoyun.base.Result;
import cn.edu.fzu.daoyun.constant.ResultCodeEnum;
import cn.edu.fzu.daoyun.dto.MenuDTO;
import cn.edu.fzu.daoyun.entity.MenuDO;
import cn.edu.fzu.daoyun.mapper.MenuMapper;
import cn.edu.fzu.daoyun.mapper.PermissionMapper;
import cn.edu.fzu.daoyun.query.setPermQuery;
import cn.edu.fzu.daoyun.query.updateMenuQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/menu")
public class MenuController {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @AnonymousGetMapping("")
    public Result getMenusTree(@RequestParam("roleId") Integer roleId){
    //    List<Integer> permissionList = permissionMapper.getPermission(roleId);
        List<MenuDO> parentMenuList = this.menuMapper.getParentMenus();
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for ( MenuDO m :parentMenuList) { // 获取二级
            MenuDTO md = new MenuDTO();
            BeanUtils.copyProperties(m,md);
            List<MenuDTO> subMenuList = this.menuMapper.getSubMenusWithOrderByPid(m.getId());
            for ( MenuDTO sm :subMenuList) { // 获取二级下的按钮权限
                List<MenuDTO> SubMenusButtonList = this.menuMapper.getSubMenusButtonPemissionWithOrderByPid(sm.getId());
                sm.setChildren(SubMenusButtonList);
            }
            md.setChildren(subMenuList);
            menuDTOList.add(md);
        }
        return Result.success(ResultCodeEnum.SUCCESS,menuDTOList);
    }


    @AnonymousPostMapping("/set")
    public Result setPermisson(@RequestBody setPermQuery query){
        this.permissionMapper.delAllByRoleId(query.getRoleId());
        for (Integer i: query.getPerm()) {
          Boolean aBoolean =  this.permissionMapper.addPerm(query.getRoleId(),i);
            if(aBoolean==false) return Result.failure(ResultCodeEnum.FAILURE);
        }
        return Result.success(ResultCodeEnum.SUCCESS);
    }

    @AnonymousGetMapping("/btn_perm")
    public Result getBtnPerm(@RequestParam("roleId") Integer roleId ){
        List<Integer> permissionList = permissionMapper.getPermission(roleId);
        List<String> stringList = new ArrayList<>();
        for ( Integer i:permissionList) {
            String btnPermit = this.menuMapper.getBtnPermit(i);
            if(btnPermit!=null) stringList.add(btnPermit);
        }
        return Result.success(ResultCodeEnum.SUCCESS,stringList);
    }

    @AnonymousPutMapping
    public Result updateMenu(@RequestBody updateMenuQuery query){
        Boolean aBoolean = this.menuMapper.updateMenu(query);
        if(aBoolean==true) return Result.success(ResultCodeEnum.SUCCESS);
        return Result.failure(ResultCodeEnum.FAILURE);

    }

}
