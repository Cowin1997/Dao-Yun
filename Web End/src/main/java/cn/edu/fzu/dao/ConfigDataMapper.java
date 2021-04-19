package cn.edu.fzu.dao;

import cn.edu.fzu.entity.DictParam;
import cn.edu.fzu.entity.SysParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConfigDataMapper {

    @Select("select * from sys_param;")
    public List<SysParam> getSysParamList();
    @Select("select * from sys_param where arg_key=#{key};")
    public SysParam getSysParamByKey(String key);
    @Select("select * from sys_param where id=#{id};")
    public SysParam getSysParamById(Integer id);
    @Delete("delete from sys_param where id=#{id};")
    public Boolean delSysParam(Integer id);
    @Insert("insert into sys_param (arg_key,arg_value,arg_desc) values (#{arg_key},#{arg_value},#{arg_desc});")
    public Boolean addSysParam(SysParam param);
    @Update("update sys_param set arg_key=#{arg_key},arg_value=#{arg_value},arg_desc=#{arg_desc} where id=#{id};")
    public Boolean updateSysParam(SysParam param);

    @Select("select * from dict where type_id is null;")
    @Results({
            @Result(column = "id", property = "id"), //有毒,加了下面的级联,id字段没有自动被封装
            @Result(column = "id", property = "subs",one=@One(select = "cn.edu.fzu.dao.ConfigDataMapper.getDictSubs"))
    })
    public List<DictParam> getDictParentList();

    @Select("select * from dict where type_id=#{id};")
    public DictParam getDictSubs(Integer id);



    @Delete("delete from dict where id=#{id} or type_id=#{id};")
    public Boolean delDictParam(Integer id);

    @Insert("insert into dict (type,detail,code,type_id) values (#{type},#{detail},#{code},#{type_id});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Boolean addDictParam(DictParam param);


    @Update("update dict set type=#{type},detail=#{detail},code=#{code},value=#{value},type_id=#{type_id} where id=#{id};")
    public Boolean updateDictParam(DictParam param);
}
