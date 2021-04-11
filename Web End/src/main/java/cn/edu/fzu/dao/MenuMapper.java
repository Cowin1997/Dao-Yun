package cn.edu.fzu.dao;


import fzu.edu.cn.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from menu where roleid like '%${roleid}%' order by ord;")
    public List<fzu.edu.cn.entity.Menu> getMenus(String roleid);

    @Select("select * from menu where parentid is null;")
    public List<fzu.edu.cn.entity.Menu> getAllMenus();


    @Select("select * from menu order by id;")
    public List<fzu.edu.cn.entity.Menu> getAllMenusOrderByIdAsc();

    @Select("select * from menu order by id;")
    public List<fzu.edu.cn.entity.Menu> getAllMenusByOrderByIdAsc();

    @Select("select * from menu where parentid=#{parentid} order by ord;")
    public List<fzu.edu.cn.entity.Menu> getMenusWithParentId(Integer parentid);


    @Select("select * from menu where parentid=#{parentid} and roleid like '%${roleid}%' order by ord;")
    public List<fzu.edu.cn.entity.Menu> getMenusWithParentIdAndroleid (Integer parentid,String roleid);
    @Select("select * from menu where id=#{mid};")
    public Menu getMenuPermission(Integer mid);
    @Update("update menu set roleid=#{roleid} where id=#{mid};")
    public Boolean updateMenuPermission(Integer mid,String roleid);
}
