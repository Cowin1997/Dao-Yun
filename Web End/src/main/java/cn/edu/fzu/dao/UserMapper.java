package cn.edu.fzu.dao;

import cn.edu.fzu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where us_username=#{username} and us_password=#{password};")
    public User getUser(String username, String password);

    @Update("update user set  us_password=#{password} where us_username=#{username};")
    public Boolean userPasswordReset(String username, String password);
}
