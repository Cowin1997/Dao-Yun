package cn.edu.fzu.dao;

import cn.edu.fzu.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where us_username=#{username} and us_password=#{password};")
    public User getUser(String username, String password);

    @Update("update user set us_password=#{password} where us_username=#{username};")
    public Boolean userPasswordResetByName(String username, String password);

    @Update("update user set us_password=#{password} where id=#{id};")
    public Boolean userPasswordResetById(String password, Integer id);

    @Select("select * from user where us_roleid=2 or us_roleid=1;")
    public List<User> getAllAdmin();


    @Insert("insert into user(us_username,us_phone,us_password,us_registtime,us_email,us_roleid,us_status) values(#{us_username},#{us_phone},#{us_password},#{us_registtime},#{us_email},#{us_roleid},#{us_status});")
    public Boolean addUser(User user);

    @Update("update user set us_username=#{us_username},us_phone=#{us_phone},us_password=#{us_password},us_registtime=#{us_registtime},us_email=#{us_email},us_roleid=#{us_roleid},us_status=#{us_status} where id=#{id};")
    public Boolean updateUser(User user);

    @Delete("delete from user where id=#{id};")
    public Boolean  delUser(Integer id);
}
