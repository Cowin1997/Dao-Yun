package cn.edu.fzu.daoyun.controller;


import cn.edu.fzu.daoyun.annotation.AnonymousGetMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPostMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPutMapping;
import cn.edu.fzu.daoyun.base.Result;
import cn.edu.fzu.daoyun.constant.ResultCodeEnum;
import cn.edu.fzu.daoyun.dto.MenuDTO;
import cn.edu.fzu.daoyun.entity.Menu;
import cn.edu.fzu.daoyun.entity.MenuDO;
import cn.edu.fzu.daoyun.mapper.Menu2Mapper;
import cn.edu.fzu.daoyun.mapper.MenuMapper;
import cn.edu.fzu.daoyun.mapper.PermissionMapper;
import cn.edu.fzu.daoyun.query.setPermQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/menu")
public class MenuController {
    @Resource
    private Menu2Mapper menu2Mapper;
    @Resource
    private PermissionMapper permissionMapper;

    @AnonymousGetMapping("")
    public Result getMenusTree(@RequestParam("roleId") Integer roleId){
        List<Integer> permissionList = permissionMapper.getPermission(1);
        List<MenuDO> parentMenuList = this.menu2Mapper.getParentMenusWithOrderById(permissionList);
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for ( MenuDO m :parentMenuList) { // 获取二级
            MenuDTO md = new MenuDTO();
            BeanUtils.copyProperties(m,md);
            List<MenuDTO> subMenuList = this.menu2Mapper.getSubMenusWithOrderByPid(m.getId());
            for ( MenuDTO sm :subMenuList) { // 获取二级下的按钮权限
                List<MenuDTO> SubMenusButtonList = this.menu2Mapper.getSubMenusButtonPemissionWithOrderByPid(sm.getId());
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

}
