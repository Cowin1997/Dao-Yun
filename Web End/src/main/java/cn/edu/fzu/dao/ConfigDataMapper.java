package cn.edu.fzu.dao;

import cn.edu.fzu.entity.DictParam;
import cn.edu.fzu.entity.SysParam;
import cn.edu.fzu.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConfigDataMapper {

    @Select("select * from sys_param limit #{from},#{to};")
    @Results(id = "sysParamMap",value = {
            @Result(column = "creator",property = "creatorId"),
            @Result(column = "reviser",property = "reviserId"),
            @Result(column = "creator",property = "creator",one=@One(select = "cn.edu.fzu.dao.ConfigDataMapper.getUser")),
            @Result(column = "reviser",property = "reviser",one=@One(select = "cn.edu.fzu.dao.ConfigDataMapper.getUser"))
    })
    public List<SysParam> getSysParamList(Integer from,Integer to);


    @Select("select us_username from user where id=#{id};")
    public String getUser(Integer id);


    @Select("select count(*) from sys_param;")
    public Integer getSysParamTotal();
    
    @Select("select * from sys_param where arg_key=#{key};")
    @ResultMap(value = {"sysParamMap"})
    public SysParam getSysParamByKey(String key);
    
    
    @Select("select * from sys_param where id=#{id};")
    public SysParam getSysParamById(Integer id);

    @Delete("delete from sys_param where id=#{id};")
    public Boolean delSysParam(Integer id);

    @Insert("insert into sys_param (arg_key,arg_value,arg_desc,creator,last_revise_time) values (#{arg_key},#{arg_value},#{arg_desc},#{creator},#{last_revise_time});")
    public Boolean addSysParam(SysParam param);

    @Update("update sys_param set arg_key=#{arg_key},arg_value=#{arg_value},arg_desc=#{arg_desc},reviser=#{reviser},last_revise_time=#{last_revise_time} where id=#{id};")
    public Boolean updateSysParam(SysParam param);

    
    @Select("select * from dict where type_id is null limit #{from},#{to};")
    @Results(id="dictParamMap",value = {
            @Result(column = "creator",property = "creatorId"),
            @Result(column = "reviser",property = "reviserId"),
            @Result(column = "creator",property = "creator",one=@One(select = "cn.edu.fzu.dao.ConfigDataMapper.getUser")),
            @Result(column = "reviser",property = "reviser",one=@One(select = "cn.edu.fzu.dao.ConfigDataMapper.getUser")),
            @Result(column = "id", property = "id"), //有毒,加了下面的级联,id字段没有自动被封装
            @Result(column = "id", property = "subs",one=@One(select = "cn.edu.fzu.dao.ConfigDataMapper.getDictSubs"))


    })
    public List<DictParam> getDictParentList(Integer from , Integer to);

    @Select("select * from dict where type_id=#{id};")
    @ResultMap(value={"dictParamMap"})
    public DictParam getDictSubs(Integer id);

    @Select("select count(*) from dict where type_id is NULL;")
    public Integer getDictParamTotal();

    @Delete("delete from dict where id=#{id} or type_id=#{id};")
    public Boolean delDictParam(Integer id);

    @Insert("insert into dict (type,detail,code,type_id,creator,last_revise_time) values (#{type},#{detail},#{code},#{type_id},#{creatorId},#{last_revise_time});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean addDictParam(DictParam param);

    @Update("update dict set type=#{type},detail=#{detail},code=#{code},value=#{value},reviser=#{reviserId},last_revise_time=#{last_revise_time} where id=#{id};")
    public Boolean updateDictParam(DictParam param);
}
