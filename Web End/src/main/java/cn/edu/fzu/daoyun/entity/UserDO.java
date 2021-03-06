package cn.edu.fzu.daoyun.entity;

import cn.edu.fzu.daoyun.base.BaseDO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.models.auth.In;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDO extends BaseDO implements Serializable {

    private String nickname;
    @Size(max = 255, message = "最大长度为255")
    private String avatar;
    @NotNull
    private Integer role_id;
    @NotNull
    private Boolean enabled;
    private String phone;


    public UserDO(Integer id, @NotNull Date gmt_create, @NotNull Date gmt_modified, String nickname, String avatar, Integer role_id, Boolean enabled) {
        super(id, gmt_create, gmt_modified);
        this.nickname = nickname;
        this.avatar = avatar;
        this.role_id = role_id;
        this.enabled = enabled;
    }

}
