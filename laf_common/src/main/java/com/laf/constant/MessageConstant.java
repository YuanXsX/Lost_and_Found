package com.laf.constant;

/**
 * 消息常量类，定义系统中使用的各种消息内容
 */
public class MessageConstant {
    // 用户相关
    public static final String USER_NOT_FOUND = "用户不存在";
    public static final String USERNAME_OR_PASSWORD_ERROR = "用户名或密码错误";
    public static final String USER_ALREADY_EXISTS = "用户已存在";
    public static final String USER_REGISTER_SUCCESS = "注册成功";
    public static final String USER_LOGIN_SUCCESS = "登录成功";
    public static final String USER_LOGOUT_SUCCESS = "退出登录成功";
    public static final String UNAUTHORIZED = "未授权访问";

    // 失物招领信息相关
    public static final String LOST_ITEM_PUBLISH_SUCCESS = "失物信息发布成功";
    public static final String FOUND_ITEM_PUBLISH_SUCCESS = "招领信息发布成功";
    public static final String ITEM_EDIT_SUCCESS = "信息编辑成功";
    public static final String ITEM_DELETE_SUCCESS = "信息删除成功";
    public static final String ITEM_NOT_FOUND = "未找到相关信息";

    // 认领相关
    public static final String CLAIM_REQUEST_SUCCESS = "认领申请已提交";
    public static final String CLAIM_APPROVED = "认领申请已通过";
    public static final String CLAIM_REJECTED = "认领申请被拒绝";
    public static final String CLAIM_ALREADY_EXISTS = "已提交认领申请，请勿重复操作";
    public static final String CLAIM_NOT_FOUND = "认领信息不存在";

    // 系统管理相关
    public static final String OPERATION_SUCCESS = "操作成功";
    public static final String OPERATION_FAILED = "操作失败";
    public static final String DATA_BACKUP_SUCCESS = "数据备份成功";
    public static final String DATA_RESTORE_SUCCESS = "数据恢复成功";
    public static final String DISPUTE_SUBMITTED = "争议已提交，管理员将尽快处理";

    // 通用
    public static final String PARAMETER_ERROR = "参数错误";
    public static final String SERVER_ERROR = "服务器内部错误";
}
