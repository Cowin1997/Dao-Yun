package cn.edu.fzu.daoyun.mapper;

import cn.edu.fzu.daoyun.entity.UserAuthDO;
import cn.edu.fzu.daoyun.entity.UserDO;
import cn.edu.fzu.daoyun.query.AddStudentQuery;
import cn.edu.fzu.daoyun.query.AddTeacherQuery;
import cn.edu.fzu.daoyun.query.addUserQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user_auth where id=#{id};")
    public UserAuthDO getUserAuthById(Integer id);
    @Update("update user_auth set credential=#{newPass} where id=#{id};")
    public Boolean updatePwd2(Integer id,String newPass);
    @Update("update user set nickname=#{name} where id=#{id};")
    public Boolean updateName(Integer id,String name);

    @Update("update user set phone=#{phone} where id=#{id};")
    public Boolean updatePhone(Integer id,String phone);

    @Select("select * from user where id=#{id};")
    public UserDO selectUserById(Integer id);

    @Update("update user set enabled = 0 where id=#{id};")
    public Boolean lockAccountById(Integer id);
    @Update("update user set enabled = 1 where id=#{id};")
    public Boolean unlockAccountById(Integer id);
    @Update("update user set enabled = 0 where role_id=#{roleId};")
    public Boolean lockAccountByRoleType(Integer roleId);

    @Select("select * from user_auth where identifier=#{identifier};")
    public UserAuthDO selectUserAuthByIdentifier(String identifier);

    @Insert("insert into user(nickname,phone,avatar,role_id,enabled,gmt_create,gmt_modified) values (" +
            "#{nickname},#{phone},#{avatar},#{role_id},#{enabled},#{gmt_create},#{gmt_modified});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean insertUser(UserDO user);


    @Insert("insert into user(nickname,phone,role_id,enabled,gmt_create) values (" +
            "#{name},#{phone},#{role},#{enabled},#{gmt_create});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean addtUser(addUserQuery user);

    @Insert("insert into user(nickname,phone,role_id,enabled,gmt_create) values (" +
            "#{name},#{phone},#{role},#{enabled},#{gmt_create});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean addUser(AddStudentQuery query);

    @Insert("insert into user(nickname,phone,role_id,enabled,gmt_create) values (" +
            "#{name},#{phone},#{role},#{enabled},#{gmt_create});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean addUser2(AddTeacherQuery query);

    @Select("select * from user where nickname=#{name};")
    public UserDO getUserByName(String name);

    @Select("select * from user where phone=#{phone};")
    public UserDO getUserByPhone(String phone);


    @Insert("insert into user_auth(user_id,identity_type,identifier,credential,gmt_create,gmt_modified) values (" +
            "#{user_id},#{identity_type},#{identifier},#{credential},#{gmt_create},#{gmt_modified});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean insertUserAuth(UserAuthDO userAuth);

    @Update("update user_auth set credential=#{newPass} where identifier=#{phone};")
    public Boolean updatePwd(String phone, String newPass);

    @Select("select count(*) from user;")
    public Integer getTotal();

    @Select("select * from user limit #{from},#{to};")
    public List<UserDO> getUserList(Integer from, Integer to);

    @Select("select * from user where nickname like '%${search}%' or phone like '%${search}%' limit #{from},#{to};")
    public List<UserDO> getUserListBySearch(Integer from, Integer to,String search);
}
