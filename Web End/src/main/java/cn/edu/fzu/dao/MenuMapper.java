package cn.edu.fzu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from menu;")
    public List<fzu.edu.cn.entity.Menu> getMenus();
}
