package cn.edu.fzu.daoyun.service;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.dto.RoleDTO;
import cn.edu.fzu.daoyun.entity.RoleDO;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleService {
    /**
     *  查询全部角色
     * @return
     */
    public Page<RoleDO> getRoleList(Integer page, Integer size);
    public Page<RoleDO> getRoleListBySearch(Integer page, Integer size,String search);

    /**
     *  添加角色
     */

    public Boolean addRole(RoleDO role);


    /**
     *  根据角色id删除角色
     *  成功返回true,失败返回false
     */
    public Boolean delRole(Integer roleId);


    /**
     *     更新角色信息
     *     成功返回true,失败返回false
     */

    public Boolean updRole(RoleDO role);
}
