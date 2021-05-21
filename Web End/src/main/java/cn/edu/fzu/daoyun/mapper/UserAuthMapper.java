package cn.edu.fzu.daoyun.mapper;

import cn.edu.fzu.daoyun.entity.UserAuthDO;
import cn.edu.fzu.daoyun.query.addUserQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper
public interface UserAuthMapper {
    @Select("select * from user_auth where identifier=#{identifier};")
    public UserAuthDO selectByIdentifier(String identifier,String type);
    @Update("update user_auth set identifier=#{name} where identity_type='local' and user_id=#{id};")
    public Boolean updateIdentifierLocal(Integer id,String name);

    @Update("update user_auth set identifier=#{phone} where identity_type='phone'and user_id=#{id};")
    public Boolean updateIdentifierPhone(Integer id,String phone);
    @Update("update user_auth set credential=#{pwd} where  user_id=#{id};")
    public Boolean updatePwdAll(Integer id,String pwd);

    @Insert("insert into user_auth(user_id, identity_type, identifier, credential, gmt_create)" +
            "values(#{id},'local',#{name},'$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK',#{gmt_create}); ")
    public Boolean addUserAuthLocal(addUserQuery query);

    @Insert("insert into user_auth(user_id, identity_type, identifier, credential, gmt_create)" +
            "values(#{id},'phone',#{phone},'$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK',#{gmt_create}); ")
    public Boolean addUserAuthPhone(addUserQuery query);

    @Insert("insert into user_auth(user_id, identity_type, identifier, credential, gmt_create)" +
            "values(#{id},'local',#{name},'$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK',#{gmt_create}); ")
    public Boolean addUserAuthLocal2(Integer id, String name, Date gmt_create);

    @Insert("insert into user_auth(user_id, identity_type, identifier, credential, gmt_create)" +
            "values(#{id},'phone',#{phone},'$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK',#{gmt_create}); ")
    public Boolean addUserAuthPhone2(Integer id, String phone, Date gmt_create);
}
