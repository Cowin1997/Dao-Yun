package cn.edu.fzu.daoyun.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(2000,"请求成功"),
    CAPTCHA_SUCCESS(2002,"验证码获取成功"),
    LOGIN_SUCCESS(2001,"登录成功"),
    REGIST_SUCCESS(2002,"注册成功"),
    PWDRESET_SUCCESS(2003,"密码重置成功"),
    ADD_SUCCESS(2004,"添加成功"),
    DEL_SUCCESS(2005,"删除成功"),
    UPD_SUCCESS(2006,"更新成功"),
    CHECK_SUCCESS(2007,"签到成功"),

    FAILURE(4000,"请求失败"),
    LOGIN_INVALID(4001,"用户名或密码错误"),
    LOGIN_DISABLED(4002,"账户被禁用"),
    LOGIN_LOCKED(4003,"账户被锁定"),
    LOGIN_FAILURE(4004,"登录失败"),
    CAPTCHA_FAILURE(4500,"验证码获取失败"),
    INVALID_PARAM(4501,"请求参数错误"),
    CAPTCHA_EXPIRED(4502,"验证码过期"),
    CAPTCHA_INVAILD(4503,"验证码错误"),
    CODE_INVAILD(4505,"短信验证码错误"),
    CODE_EXPIRED(4506,"短信验证码过期"),
    TOKEN_INVALID(4504,"无效的token"),
    PWDRESET_FAILURE(4507,"密码重置失败"),
    ADD_FAILURE(4508,"添加失败"),
    DEL_FAILURE(4509,"删除失败"),
    UPD_FAILURE(4510,"更新失败"),
    LOGIN_UNEXIST(4511,"账户不存在,请先注册"),
    STUDENT_UNEXIST(4512,"查询的学生不存在"),
    TEACHER_UNEXIST(4513,"教师ID不存在"),
    COURSE_UNEXIST(4514,"查询的班课号不存在"),
    CHECK_FAILURE(2008,"签到失败"),
    CHECK_TIMEOUT(20009, "当前没有签到任务"),
    HAS_CHECK(20006,"已签到,请勿重复签到"),
    HASNOT_UNCHECK(26561,"当前没有签到任务"),

    ;
    //响应状态码
    private Integer code;
    //响应信息
    private String message;
}
