package cn.edu.fzu.utils;

public class ResultUtils {
    public static class STATUS{
        public static Integer SUCCESS = 0;
        public static Integer ERROR = -1;
    }
    public static class MESSAGE {
        public static String GET_SUCCESS = "获取成功";
        public static String GET_ERROR = "获取失败";
        public static String UPDATE_SUCCESS = "更新成功";
        public static String UPDATE_ERROR= "更新失败";
        public static String ADD_SUCCESS = "添加成功";
        public static String ADD_ERROR= "添加失败";
        public static String RESIST_SUCCESS="注册成功";
        public static String RESIST_SID_EXIST="学号已经注册";
        public static String RESIST_ERROR="注册失败";
        public static String RESIST_PHONE_EXIST="手机号已被注册";
        public static String LOGIN_SUCCESS = "登录成功";
        public static String RESIST_USERNAME_EXIST="用户名已经被注册";
        public static String LOGIN_ERROR_ACCOUNT_PASS = "用户名或密码错误";
        public static String DEL_SUCCESS="删除成功";
        public static String DEL_ERROR="删除失败";
        public static String CLASS_IS_EXIST="班课号已存在";
        public static String INVLID_CODE="验证码错误";
        public static String CREATE_SUCCESS="创建成功";
        public static String CREATE_ERROR="创建失败";
        public static String CHECK_ALREADY="请勿重复签到";
        public static String CHECK_SUCCESS="签到成功";
        public static String CHECK_HAD_END="签到已经停止";
        public static String CHECK_ERROR="签到失败";
        public static String SELECT_COURSE_EXIST="请勿重复选择班课";
        public static String SELECT_COURSE_SUCCESS="加入班课成功";
        public static String SELECT_COURSE_ERROR="加入班课失败";
        public static String STUDENT_EXIST="该学号已被绑定";
        public static String SYS_PARAM_KEY_NO_EXIST="系统参数键不存在";
        public static String SYS_PARAM_KEY_EXIST="系统参数键已存在";
        public static String PASSWORD_NOT_CONSISTENT="两次输入的密码不一致";
        public static String REQUEST_PARAM_ERROR = "请求参数错误";
        public static String SESSION_ERROR="会话错误";
        public static String ACCOUNT_NOT_EXIST="账号不存在";
    }

}
