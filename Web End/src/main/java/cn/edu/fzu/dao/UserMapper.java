package cn.edu.fzu.dao;

import cn.edu.fzu.entity.User;
import cn.edu.fzu.param.RegistReq;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where us_username=#{username} and us_password=#{password};")
    public User getUserByNameAndPassWord(String username, String password);

    @Select("select * from user where us_username=#{username};")
    public User getUserByName(String username);

    @Select("select * from user where id=#{id};")
    public User getUserById(Integer id);

    @Select("select * from user where us_phone=#{phone};")
    public User getUserByPhone(String phone);

    @Select("select * from user where us_id=#{sid};")
    public User getUserBySid(String sid);

    @Update("update user set us_password=#{password} where id=#{id};")
    public Boolean userPasswordResetById(String password, Integer id);

    @Update("update user set us_password=#{newPass} where us_phone=#{phone};")
    public Boolean userPasswordResetByPhone(String phone, String newPass);

    @Insert("insert into user(us_username,us_phone,us_password,us_registtime,us_email,us_roleid,us_status,us_id) values(#{username},#{phone},#{password},#{registTime},#{email},#{type},#{status},#{identity});")
    public Boolean registUser(RegistReq req);

    @Select("select * from user limit #{from},#{to};")
   public List<User> getUserList(Integer from, Integer to);
    @Select("select count(*) from user;")
    public Integer getTotalUser();

    @Insert("insert into user(us_username,us_phone,us_password,us_registtime,us_email,us_roleid,us_status) values(#{us_username},#{us_phone},#{us_password},#{us_registtime},#{us_email},#{us_roleid},#{us_status});")
    public Boolean addUser(User user);
}
