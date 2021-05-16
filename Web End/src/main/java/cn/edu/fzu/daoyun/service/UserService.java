package cn.edu.fzu.daoyun.service;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.dto.UserDTO;
import cn.edu.fzu.daoyun.entity.UserDO;

import java.util.List;

public interface UserService {
    public UserDO getUserById(Integer id);
    public UserDTO getUserDetailByIdentifier(String identifier,String type);
    public Page<UserDTO> getUserList(Integer page, Integer size);
}
