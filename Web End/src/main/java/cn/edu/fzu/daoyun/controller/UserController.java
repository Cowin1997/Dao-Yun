package cn.edu.fzu.daoyun.controller;

import cn.edu.fzu.daoyun.annotation.AnonymousGetMapping;
import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.base.Result;
import cn.edu.fzu.daoyun.constant.ResultCodeEnum;
import cn.edu.fzu.daoyun.dto.UserDTO;
import cn.edu.fzu.daoyun.mapper.UserMapper;
import cn.edu.fzu.daoyun.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags={"用户信息接口"})
@RequestMapping(value = {"/api/user"})
public class UserController {
    @Resource
    private UserService userService;

    @AnonymousGetMapping("")
    public Result<Page> getUserPage(@RequestParam(value = "page") Integer page,@RequestParam(value = "size") Integer size){
        Page<UserDTO> userList = this.userService.getUserList(page, size);
        return Result.success(ResultCodeEnum.SUCCESS, userList);

    }


}
