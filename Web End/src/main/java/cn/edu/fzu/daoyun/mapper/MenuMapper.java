package cn.edu.fzu.daoyun.mapper;

import cn.edu.fzu.daoyun.dto.MenuDTO;
import cn.edu.fzu.daoyun.entity.Menu;
import cn.edu.fzu.daoyun.entity.MenuDO;
import cn.edu.fzu.daoyun.query.updateMenuQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MenuMapper {



    @Select("<script> select * from menu where id in"+
    "<foreach  item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach> </script>")
    public List<MenuDO> getMenusById(List<Integer> ids);

    @Select("select * from menu where parent_id is NULL;")
    public List<MenuDO> getParentMenus();

    @Select("<script> select * from menu where id in"+
            "<foreach  item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach> and parent_id is NULL ORDER BY menu_sort asc;  </script>")
    public List<MenuDO> getParentMenusWithOrderById(List<Integer> ids);

    @Select("select * from menu where parent_id=#{pid}  order by menu_sort asc;")
    public List<MenuDTO> getSubMenusWithOrderByPid(Integer pid);


    @Select("select * from menu where parent_id=#{pid} order by menu_sort asc;")
    public List<MenuDTO> getSubMenusButtonPemissionWithOrderByPid(Integer pid);
    @Select("<script> select * from menu where parent_id=#{pid} and " +
            "id in <foreach  item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach>" +
            " order by menu_sort asc; </script>")
    public List<MenuDTO> getSubMenusWithOrderByPida( List<Integer> ids,Integer pid);

    @Select("select permission from menu where id=#{i} and type=2;")
    public String getBtnPermit(int i);



    @Update("update menu set icon=#{icon},title=#{title},path=#{path},menu_sort=#{menu_sort} where id=#{id};")
    public Boolean updateMenu(updateMenuQuery query);
}
