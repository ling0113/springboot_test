package com.springboot_test.util;

import lombok.Getter;

/**
 * 全局常量类
 *
 * @author Tanwei
 * @date 2019-06-15 17:32
 */
public interface Constants {

    /**
     * root管理员
     */
    String ADMIN_ROOT = "root";
    /**
     * 默认排序
     */
    Integer DEFAULT_SORT = 0;
    /**
     * 默认版本号
     */
    Integer DEFAULT_REVISION = 0;
    /**
     * 启用
     */
    Integer ENABLE_FLAG_YES = 1;
    /**
     * 禁用
     */
    Integer ENABLE_FLAG_NO = 0;
    /**
     * 离职
     */
    Integer USERSTATUS = 0;
    /**
     * 部门
     */
    Integer ORG_DEPT = 1;
    /**
     * 菜单根目录
     */
    String MENU_ROOT = "root";
    /**
     * 菜单根目录
     */
    String ORG_ROOT = "root";
    /**
     * 默认小程序资源配置
     */
    String DEFAULT_APPSETTING_ID = "1";
    /**
     * 系统类型Tab
     */
    String SYSTEM_TAB_MODULE_TYPE = "system";
    /**
     * 自定义类型Tab（小程序）
     */
    String CUSTOM_TAB_MODULE_TYPE_MINI = "mini";
    /**
     * 自定义TabModuleID
     */
    String CUSTOM_TAB_MODULE_ID = "16";
    /**
     * 公司全称截取后面的“责任有限公司”和“有限公司”
     */
    String COMPANY_NAME_SPLIT_01 = "责任有限公司";
    String COMPANY_NAME_SPLIT_02 = "有限公司";
    /**
     * 默认用户密码
     */
    String DEFAULT_PASSWORD = "000000";
    /**
     * 默认代理商（管理员）角色ID
     */
    String DEFAULT_AGENT_ROLEID = "200000";
    /**
     * 默认普通商户（管理员）角色ID
     */
    String DEFAULT_GENER_ROLEID = "200001";
    /**
     * 默认普通员工角色ID
     */
    String DEFAULT_STAFF_ROLEID = "200002";

    /**
     * 易装宝基础职位代码code定义 p_designer : 设计师 p_manager: 项目经理: p_manager p_director: 项目主管
     */
    String ROLE_DESIGNER = "p_designer";
    String ROLE_MANAGER = "p_manager";
    String ROLE_DIRECTOR = "p_director";

    /**
     * 工作流审核常量 Pass: 通过 Reject: 驳回
     */
    String CHECK_PASS = "Pass";
    String CHECK_REJECT = "Reject";

    /**
     * 轨迹来源Id
     */
    String SOURCE_ID = "source_id";
    /**
     * 浏览次数
     */
    String VIEW_TIME = "viewTime";

    String[] EXCLUDE_ROLES = new String[]{
        DEFAULT_AGENT_ROLEID,
        DEFAULT_GENER_ROLEID,
    };
    /**
     * 申请状态
     */
    Integer APPLY_UNHANDLE = 0;
    Integer APPLY_FINISH = 1;
    /**
     * 角色类型 0：系统创建 1：代理商创建
     */
    Integer ROLE_TYPE_SYS = 0;
    Integer ROLE_TYPE_CUSTOM = 1;
    /**
     * 菜单类型 0：后台菜单 1：小程序菜单
     */
    String TYPE_BACKEND = "1";
    String TYPE_MINIAPP = "2";


    Integer NUMBER_ZERO = 0;
    Integer NUMBER_ONE = 1;
    Integer NUMBER_TWO = 2;
    Integer NUMBER_THREE = 3;
    Integer NUMBER_FOUR = 4;
    /**
     * 默认个人简介
     */
    String defaultProfile = "欢迎来到我的个人名片，我是%s的%s。您可以通过名片中的官网、企业动态、案例更多的了解我们，如有业务合作或者其他问题，请通过咨询功能与我联系，我会第一时间回复您！";
    /**
     * 默认名片链接地址
     */
    String defaultCardLink = "pages/home/index?activeTab=card&cardUserId=%s";
    /**
     * 默认样式
     */
    String defaultStyleId = "1";
    /**
     * 案例分类默认值,0顶级分类
     */
    String dynamicParentId = "0";
    /**
     * 案例默认状态 0 未发布 1已发布
     */
    Integer CASE_DEFAULT_STATUS = 0;
    Integer CASE_RELEASE_STATUS = 1;
    /**
     * 对应company表中商户类型 0 总代理 1 代理商 2 商户
     */
    Integer SOLE_AGENT = 0;
    Integer AGENT = 1;
    Integer MERCHANT = 2;
    /**
     * 试用版商户最大人数100人
     */
    Integer MAX_EXPERIENCE_NUMBER = 100;
     /**
     * 试用版商户默认开通两个月
     */
    Integer MAX_EXPERIENCE_MONTH = 2;
    /**
     * 商户状态 -1：删除/禁用\n0:未开通1：正常\n2：过期 1 正常开通
     */
    Integer MERCHANT_STATUS_DELETE = -1;
    Integer MERCHANT_STATUS_UNOPEN = 0;
    Integer MERCHANT_STATUS_OPEN = 1;
    Integer MERCHANT_STATUS_EXPIRED = 2;

    /**
     * 客户类型 0：游客登录  1：授权客户  2：认证客户
     */
    Byte USER_CUST_VISITOR = 0;
    Byte USER_CUST_ATTEST = 1;
    Byte USER_CUST_AUTH = 2;
    /**
     * 创建操作
     */
    String OPERAT_CREATE = "create";
    /**
     * 更新操作
     */
    String OPERAT_UPDATE = "update";

    /**
     * 访客默认昵称
     */
    public static final String DEFAULT_CUSTNAME = "游客";
    /**
     * 默认头像
     */
    String DEFAULT_IMAGE = "https://sycexe-keda.oss-cn-shenzhen.aliyuncs.com/keda-static/default_avatar@2x.png";

    /**
     *  三叶草平台
     */
    public static String adminMerchant = "1002";

    /**
     * 全局状态码
     */
    @Getter
    enum StatusCode {
        SUCCESS("200", "调用成功"),
        BAD_REQUEST("400", "坏请求"),
        UNAUTHORIZED("401", "权限不足"),
        SOURCE_NOT_FOUND("404", "资源不存在"),
        METHOD_NOT_ALLOWED("405", "Method 方法不支持"),
        CONFLICTS("409", "通用冲突"),
        REQUEST_BODY_NULL("414", "请求体为空"),
        SYSTEM_ERROR("500", "系统异常"),
        SERVICE_NOT_AVAILABLE("503", "服务当前无法处理请求"),
        AUTHENTICATED_ERROR("1001", "用户名/密码错误"),
        REMOTE_CALL_FAILED("1002", "远程调用失败"),
        REPEAT_OPERATION("1003", "重复操作"),
        INVALID_USER("1004", "用户不存在"),
        DISABLED_USER("1005", "用户被禁用"),
        LEAVE_USER("1006", "用户已离职"),
        BINDING_USER("1007", "用户已被绑定"),
        VERIFICATION_CODE("1008", "验证码错误"),
        ILLEGAL_REQUEST("1009", "非法请求"),
        INVALID_TOKEN("1010", "登录失效"),
        USER_ALREADY_EXISTS("1011", "该手机号已被其他用户绑定"),
        MUTLI_COMPANY("1012", "多商户"),
        FILE_UPLOAD_ERROR("1020", "文件上传失败"),
        INVALID_GRANT_TYPE("1040", "grant_type不合法"),
        INVALID_CLIENT("1042", "无效的client_id或client_secret"),
        INVALID_APPSETTING("1043", "获取小程序配置失败"),
        INVALID_IMAGE("1044", "图片违规"),
        INVALID_TEXT("1045", "内容违规"),
        JOB_CLASS_EXIST("1046", "定时任务类名已存在"),
        ADD_ERROR("1047", "添加失败"),
        UPDATE_ERROR("1048", "编辑失败"),
        DELETE_ERROR("1049", "删除失败"),
        QUERY_ERROR("1050", "查询失败"),
        PARAMETER_ERROR("1051", "参数有误");

        private String code;
        private String message;

        private StatusCode(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
