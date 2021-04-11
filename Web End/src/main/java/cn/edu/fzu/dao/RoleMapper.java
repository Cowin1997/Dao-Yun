package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select * from role;")
    public List<Role> getAllRole();


}
