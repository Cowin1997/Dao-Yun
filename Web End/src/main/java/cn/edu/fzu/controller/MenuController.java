package cn.edu.fzu.controller;

import cn.edu.fzu.dao.MenuMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
    @Resource
    private MenuMapper menuMapper;


    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<fzu.edu.cn.entity.Menu> getMenus(){
        return this.menuMapper.getMenus();
    }
}
