package site.achun.audio.client.constant;


import site.achun.support.api.response.RspCodeInfo;

/**
 * 错误码和本项目扩展错误码
 *
 * @Author: Heiffeng
 * @Date: 2021/12/13 11:11
 */
public class AudioRC{
    public static final RspCodeInfo PARAMS_IS_NULL = new RspCodeInfo("CM001", "参数为空");
    public static final RspCodeInfo EXISTS = new RspCodeInfo("CM002", "");
    public static final RspCodeInfo NOT_EXISTS = new RspCodeInfo("CM003", "");
    public static final RspCodeInfo FAILED_VALIDATION = new RspCodeInfo("CM004", "无法通过验证");
    public static final RspCodeInfo DB_CURD_ERROR = new RspCodeInfo("CM101", "数据库CURD错误");
    public static final RspCodeInfo DB_QUERY_RESULT_EMPTY = new RspCodeInfo("CM102", "查询结果为空");
    public static final RspCodeInfo DATA_IS_NULL = new RspCodeInfo("CM902", "返回数据(DATA)为空");
    public static final RspCodeInfo CODE_UN_IMPLEMENTS = new RspCodeInfo("CM901", "业务代码暂未实现");

    // 分组相关 GL1 开头
    public static final RspCodeInfo GROUP_NOT_EXIST = new RspCodeInfo("GL101", "分组不存在");

    // 相册相关 GL2 开头
    public static final RspCodeInfo ALBUM_NOT_EXIST = new RspCodeInfo("GL201", "相册不存在");
    public static final RspCodeInfo ALBUM_EXIST = new RspCodeInfo("GL202", "相册不存在");
    public static final RspCodeInfo ALBUM_RECORD_NOT_EMPTY = new RspCodeInfo("GL203", "相册内容不为空");

    // 画板相关 GL3 开头
    public static final RspCodeInfo BOARD_NOT_EXIST = new RspCodeInfo("GL301", "画板不存在");
    public static final RspCodeInfo BOARD_EXIST = new RspCodeInfo("GL302", "画板不存在");
    public static final RspCodeInfo BOARD_RECORD_NOT_EMPTY = new RspCodeInfo("GL303", "画板内容不为空");

}
