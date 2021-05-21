package cn.edu.fzu.daoyun.daoyun;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.config.security.SecurityProperties;
import cn.edu.fzu.daoyun.dto.MenuDTO;
import cn.edu.fzu.daoyun.dto.SysDictDTO;
import cn.edu.fzu.daoyun.entity.Menu;
import cn.edu.fzu.daoyun.entity.MenuDO;
import cn.edu.fzu.daoyun.entity.SysDictDO;
import cn.edu.fzu.daoyun.mapper.MenuMapper;
import cn.edu.fzu.daoyun.mapper.PermissionMapper;
import cn.edu.fzu.daoyun.service.SysDictService;
import cn.edu.fzu.daoyun.utils.captcha.CaptChaUtils;
import cn.edu.fzu.daoyun.utils.JwtUtils;
import cn.edu.fzu.daoyun.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DaoYunApplicationTests {
    @Resource
    private  PasswordEncoder passwordEncoder;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private CaptChaUtils captChaUtils;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private SysDictService sysDictService;


    @Resource
    private MenuMapper menuMapper;
    @Resource
    private PermissionMapper permissionMapper;


    @Test
    void tetttt(){
        List<Integer> permissionList = permissionMapper.getPermission(1);
        List<MenuDO> parentMenuList = this.menuMapper.getParentMenusWithOrderById(permissionList);
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for ( MenuDO m :parentMenuList) { // 获取二级
            MenuDTO md = new MenuDTO();
            BeanUtils.copyProperties(m,md);
            List<MenuDTO> subMenuList = this.menuMapper.getSubMenusWithOrderByPid(m.getId());
            for ( MenuDTO sm :subMenuList) { // 获取二级下的按钮权限
                List<MenuDTO> SubMenusButtonList = this.menuMapper.getSubMenusButtonPemissionWithOrderByPid(sm.getId());
                sm.setChildren(SubMenusButtonList);
            }
            md.setChildren(subMenuList);
            menuDTOList.add(md);
        }
        System.out.println(menuDTOList.toString());






    }







    @Test
    void contextLoads() {
        System.out.println(securityProperties.toString());
    }
    @Test
    void testRedis01(){
        redisUtils.set("key",new Date());
        System.out.println(redisUtils.get("key"));
    }
    @Test
    void testCaptcha(){
        System.out.println(captChaUtils.getCaptcha().toBase64());
    }

    @Test
    void test001(){
        String pwd =  this.passwordEncoder.encode("123456");
        System.out.println(pwd);
    }

}
