package cn.edu.fzu.daoyun.service.impl;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.constant.RoleEnum;
import cn.edu.fzu.daoyun.dto.MenuDTO;
import cn.edu.fzu.daoyun.dto.UserDTO;
import cn.edu.fzu.daoyun.entity.*;
import cn.edu.fzu.daoyun.exception.BadRequestException;
import cn.edu.fzu.daoyun.mapper.*;
import cn.edu.fzu.daoyun.query.addUserQuery;
import cn.edu.fzu.daoyun.query.updateUserQuery;
import cn.edu.fzu.daoyun.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl  {
    @Resource
    private UserAuthMapper userAuthMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private MenuMapper menuMapper;


    @Transactional
    public Boolean updateUserInfo(updateUserQuery query){
        Boolean aBoolean = this.userMapper.updateName(query.getId(), query.getName());
        if(aBoolean==false) throw new RuntimeException();
        aBoolean = this.userMapper.updatePhone(query.getId(), query.getPhone());
        if(aBoolean==false) throw new RuntimeException();
       aBoolean = this.userAuthMapper.updateIdentifierLocal(query.getId(), query.getName());
        if(aBoolean==false) throw new RuntimeException();
        aBoolean = this.userAuthMapper.updateIdentifierPhone(query.getId(),query.getPhone());
        if(aBoolean==false) throw new RuntimeException();
        if(query.getPassword()!=null|| !StringUtils.isEmptyOrWhitespace(query.getPassword())){
            aBoolean = this.userAuthMapper.updatePwdAll(query.getId(), new BCryptPasswordEncoder().encode(query.getPassword()));
            if(aBoolean==false) throw new RuntimeException();
        }

        return aBoolean;
    }

    @Transactional
    public Boolean addUser(addUserQuery query){
        query.setGmt_create(new Date());
        System.out.println(query.toString());
        UserDO user = this.userMapper.getUserByName(query.getName());
        if(user!=null) throw new BadRequestException("??????????????????");
        user = this.userMapper.getUserByPhone(query.getPhone());
        if(user!=null) throw new BadRequestException("??????????????????");
        // ??????
        Boolean aBoolean = this.userMapper.addtUser(query);
        if(!aBoolean) throw new RuntimeException();
        // ??????
        String passWord = new BCryptPasswordEncoder().encode("123456");
        aBoolean = this.userAuthMapper.addUserAuthLocal(query);
        if(!aBoolean) throw new RuntimeException();
        aBoolean = this.userAuthMapper.addUserAuthPhone(query);
        if(!aBoolean) throw new RuntimeException();
        return aBoolean;
    }








    /**
     *  ?????? id ????????????????????????
     */

    public UserDO getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    /*
     *  ?????? identifier ???????????????????????????
     */

    public UserDTO getUserDetailByIdentifier(String identifier,String type) {
        UserDTO userDTO = new UserDTO();
        // ?????? ??????????????????(user_auth)
        UserAuthDO userAuth = userAuthMapper.selectByIdentifier(identifier,type);
        if(userAuth==null) return null;
        userDTO.setUserAuth(userAuth);
        // ???????????????????????????(user)
        UserDO user = userMapper.selectUserById(userAuth.getUser_id());
        if(user==null) return null;
        userDTO.setUser(user);
        // ???????????????????????????
        RoleDO role = roleMapper.getRoleById(user.getRole_id());
        if(role==null) return null;
        userDTO.setUserRole(role);
        // ??????????????????(??????)
        if(user.getRole_id() == RoleEnum.STUDENT){
            StudentDO studentInfo = this.studentMapper.getStudentByUserId(user.getId());
            userDTO.setInfo(studentInfo);
        }
        //  ??????????????????(??????)
        if(user.getRole_id() == RoleEnum.TEACHER){
            TeacherDO teacherInfo = this.teacherMapper.getTeacherByUserId(user.getId());
            userDTO.setInfo(teacherInfo);
        }
        // ??????????????????
//        List<MenuDTO> permitList = new ArrayList<>();
//        List<Integer> permitMenuIdList = this.permissionMapper.getPermission(user.getRole_id());
//        if(permitMenuIdList.size()>0){
//            List<MenuDO> permitMenuDoList = this.menu2Mapper.getMenusById(permitMenuIdList);
//
//            // ??????DTO
//            for (MenuDO m:permitMenuDoList) {
//                MenuDTO menu = new MenuDTO();
//                BeanUtils.copyProperties(m,menu);
//                permitList.add(menu);
//            }
//            // permitList ?????????
//            List<MenuDTO> tree = generateMenuTree(permitList);
        List<Integer> permissionList = permissionMapper.getPermission(user.getRole_id());
        List<MenuDTO> menuDTOList = new ArrayList<>();
        if(permissionList.size()>0) {
            List<MenuDO> parentMenuList = this.menuMapper.getParentMenusWithOrderById(permissionList);
            System.out.println(parentMenuList.toString());

            for (MenuDO m : parentMenuList) { // ????????????
                MenuDTO md = new MenuDTO();
                BeanUtils.copyProperties(m, md);
                List<MenuDTO> subMenuList = this.menuMapper.getSubMenusWithOrderByPida(permissionList, m.getId());
                md.setChildren(subMenuList);
                menuDTOList.add(md);
            }

        }
        userDTO.setMenus(menuDTOList);
        return userDTO;
    }


    public Page<UserDTO> getUserList(Integer page, Integer size) {
        List<UserDTO> pageData = new ArrayList<>();
        Integer from = (page-1)*size;
        Integer to = page * size;
        Integer totalSize = this.userMapper.getTotal();
        Integer totalPage = (int) Math.ceil((double) totalSize / size);
        List<UserDO> userList = this.userMapper.getUserList(from,to);
        for (UserDO u:userList) {
            UserDTO d = new UserDTO();
            d.setUser(u);
            RoleDO role = this.roleMapper.getRoleById(u.getRole_id());
            d.setUserRole(role);
            pageData.add(d);
        }
        return new Page(pageData,totalSize,totalPage);
    }






    public Page<UserDTO> getUserListBySearch(Integer page, Integer size,String search) {
        List<UserDTO> pageData = new ArrayList<>();
        Integer from = (page-1)*size;
        Integer to = page * size;
        List<UserDO> userList = this.userMapper.getUserListBySearch(from,to,search);
        Integer totalSize = userList.size();
        Integer totalPage = (int) Math.ceil((double) totalSize / size);

        for (UserDO u:userList) {
            UserDTO d = new UserDTO();
            d.setUser(u);
            RoleDO role = this.roleMapper.getRoleById(u.getRole_id());
            d.setUserRole(role);
            pageData.add(d);
        }
        return new Page(pageData,totalSize,totalPage);
    }







    public  List<MenuDTO>  generateMenuTree(List<MenuDTO> menuList){
        List<MenuDTO> trees = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();
        for (MenuDTO menuDTO : menuList) {
            if (menuDTO.getParent_id() == null) { // ??????
                trees.add(menuDTO);
            }
            for (MenuDTO it : menuList) {
                if (menuDTO.getId().equals(it.getParent_id())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        return trees;
    }

}
