package cn.edu.fzu.dao;

import cn.edu.fzu.entity.Admin;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where ad_username=#{username} and ad_password=#{password};")
    public Admin getAdmin(String username,String password);

    @Select("select * from admin;")
    public List<Admin> getAdmins();

    @Select("select * from admin where id=#{id};")
    public Admin getAdminById(Integer id);

    @Insert("insert into admin(ad_username,ad_password,ad_phone,ad_email,ad_createtime,ad_createid,ad_issuperadmin,ad_accountstatus)" +
            "values (#{ad_username},#{ad_password},#{ad_phone},#{ad_email},#{ad_createtime},#{ad_createid},#{ad_issuperadmin},#{ad_accountstatus});")
    public Boolean addAdmin(Admin admin);


    @Delete("delete from admin where id = #{id};")
    public Boolean delAdminById(Integer id);


    @Update("update admin set ad_phone=#{ad_phone},ad_email=#{ad_email},ad_issuperadmin=#{ad_issuperadmin}" +
            ",ad_accountstatus=#{ad_accountstatus} where id = #{id};")
    public Boolean updateAdmin(Admin admin);

    @Update("update admin set ad_password=#{ad_password} where ad_username = #{ad_username};")
    public Boolean passwordRest(Admin admin);


    @Update("update admin set ad_accountstatus=0 where id = #{id};")
    public Boolean forbidAccount(Integer id);
    @Update("update admin set ad_accountstatus=1 where id = #{id};")
    public Boolean unforbidAccount(Integer id);
}
