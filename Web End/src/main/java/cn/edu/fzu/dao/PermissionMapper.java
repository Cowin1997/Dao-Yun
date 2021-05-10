package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Permission;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("select * from permission where roleid like '%${roleid}%' and menuuri=#{uri};")
    public List<Permission> getPermissionByUriAndRoleId(String roleid, String uri);

    @Select("select id from permission where roleid like '%${roleid}%';")
    public List<Integer> getPermissionIdByRoleId(String roleid);

    @Select("select * from permission;")
    public List<Permission> getAllPermission();

    @Select("select * from permission where menuid=#{menuId};")
    public List<Permission> getPermissionByMenuId(Integer menuId);

    @Select("select * from permission where menuid=#{menuId} and roleid like '%${roleid}%';")
    public List<Permission> getPermissionByMenuIdAndRoleId(Integer menuId, String roleid);


    @Update("update permission set Roleid= #{Roleid} where id=#{permissionId}")
    public Boolean setPermission(Integer permissionId, String Roleid);

    @Select("select * from permission where id=#{id};")
    public Permission getPermissionById(Integer id);


}
