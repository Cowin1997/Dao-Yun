package cn.edu.fzu.daoyun.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("select menu_id from permission where role_id=#{roleId};")
    public List<Integer> getPermission(Integer roleId);


    @Delete("delete from permission where role_id=#{roleId};")
    public Boolean delAllByRoleId(Integer roleId);
    @Insert("insert into permission(role_id,menu_id) values(#{roleId},#{menuId});")
    public Boolean addPerm(Integer roleId,Integer menuId);

}
