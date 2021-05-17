package cn.edu.fzu.daoyun.service.impl;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.dto.RoleDTO;
import cn.edu.fzu.daoyun.entity.RoleDO;
import cn.edu.fzu.daoyun.entity.SysParamDO;
import cn.edu.fzu.daoyun.mapper.RoleMapper;
import cn.edu.fzu.daoyun.mapper.UserMapper;
import cn.edu.fzu.daoyun.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;
    /**
     *  查询全部角色
     * @return
     */
    @Override
    public Page<RoleDO> getRoleList(Integer page, Integer size) {
        Integer from = (page - 1) * size;
        Integer to = page * size;

        Integer totalSize = this.roleMapper.getRoleTotal(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        List<RoleDO> roleDOList = this.roleMapper.getRoleList(from, to);
        Page pageResult = new Page(roleDOList, totalSize, totalPage);
        return pageResult;
    }

    @Override
    public Page<RoleDO> getRoleListBySearch(Integer page, Integer size, String search) {
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<RoleDO> roleDOList = this.roleMapper.getRoleListBySearch(from, to,search);
        Integer totalSize = roleDOList.size(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数

        Page pageResult = new Page(roleDOList, totalSize, totalPage);
        return pageResult;
    }

    @Override
    public Boolean addRole(RoleDO role) {
        Date create = new Date();
        role.setGmt_create(create);
        role.setGmt_modified(create);
        return this.roleMapper.addRole(role);
    }

    /**
     * 根据角色id删除角色
     * 成功返回true,失败返回false
     *
     * @param roleId
     */
    @Override
    @Transactional
    public Boolean delRole(Integer roleId) {
        this.userMapper.lockAccountByRoleType(roleId);
        return this.roleMapper.delRole(roleId);
    }

    /**
     *  根据角色id更新角色信息
     * 成功返回true,失败返回false
     *
     * @param role
     */
    @Override
    public Boolean updRole(RoleDO role) {
        Date modified = new Date();
        role.setGmt_create(modified);
        role.setGmt_modified(modified);
        return this.roleMapper.updRole(role);
    }


}
