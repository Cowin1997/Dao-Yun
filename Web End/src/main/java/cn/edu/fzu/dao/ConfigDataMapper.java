package cn.edu.fzu.dao;

import cn.edu.fzu.entity.ConfigData;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ConfigDataMapper {

    @Select("select * from configdata order by code;")
    public List<ConfigData> getConfigDatas();

    @Update("update configdata set configdata.key=#{key},configdata.value=#{value},configdata.info=#{info} where configdata.id=#{id};")
    public Boolean updateConfigData(ConfigData data);

    @Insert("insert into configdata (id,code,key,value,info) values (#{id},#{code},#{key},#{value},#{info});")
    public Boolean addConfigData(ConfigData data);

    @Select("delete from configdata where id=#{id};")
    public Boolean delete(Integer id);

    @Select("select * from configdata where code=#{code};")
    public ConfigData getByCode(Integer code);
}
