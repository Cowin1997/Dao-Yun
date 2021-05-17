package cn.edu.fzu.daoyun.controller;

import cn.edu.fzu.daoyun.annotation.AnonymousGetMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPostMapping;
import cn.edu.fzu.daoyun.annotation.AnonymousPutMapping;
import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.base.Result;
import cn.edu.fzu.daoyun.constant.ResultCodeEnum;
import cn.edu.fzu.daoyun.dto.UserDTO;
import cn.edu.fzu.daoyun.mapper.UserMapper;
import cn.edu.fzu.daoyun.query.addUserQuery;
import cn.edu.fzu.daoyun.query.updateUserQuery;
import cn.edu.fzu.daoyun.service.UserService;
import cn.edu.fzu.daoyun.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@Api(tags={"用户信息接口"})
@RequestMapping(value = {"/api/user"})
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private UserMapper userMapper;

    @AnonymousGetMapping("")
    public Result<Page> getUserPage(@RequestParam(value = "page") Integer page,@RequestParam(value = "size") Integer size,
                                    @RequestParam(value = "search",required = false) String search
                                    ){
        if(search==null|| StringUtils.isEmptyOrWhitespace(search)){
            Page<UserDTO> userList = this.userService.getUserList(page, size);
            return Result.success(ResultCodeEnum.SUCCESS, userList);
        }else{
            Page<UserDTO> userList = this.userService.getUserListBySearch(page, size,search);
            return Result.success(ResultCodeEnum.SUCCESS, userList);
        }

    }
    @GetMapping("/lock")
    public Result<Boolean> lockAccount(@RequestParam(value = "id") Integer id){

        Boolean aBoolean = this.userMapper.lockAccountById(id);
        if(aBoolean) return Result.success(ResultCodeEnum.SUCCESS);
        return Result.failure(ResultCodeEnum.FAILURE);
    }
    @GetMapping("/unlock")
    public Result<Boolean> unlockAccount(@RequestParam(value = "id") Integer id){
        Boolean aBoolean = this.userMapper.unlockAccountById(id);
        if(aBoolean) return Result.success(ResultCodeEnum.SUCCESS);
        return Result.failure(ResultCodeEnum.FAILURE);
    }

    @AnonymousPutMapping("")
    public Result updateUserInfo(@RequestBody updateUserQuery query){
        Boolean aBoolean = this.userService.updateUserInfo(query);
        if(aBoolean) return Result.success(ResultCodeEnum.SUCCESS);
        return Result.failure(ResultCodeEnum.FAILURE);

    }


    @AnonymousPostMapping("")
    public Result addUser(@RequestBody addUserQuery query){
        Boolean aBoolean = this.userService.addUser(query);
        if(aBoolean) return Result.success(ResultCodeEnum.SUCCESS);
        return Result.failure(ResultCodeEnum.FAILURE);

    }


}
