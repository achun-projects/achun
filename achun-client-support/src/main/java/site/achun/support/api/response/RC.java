package site.achun.support.api.response;

import site.achun.support.api.response.RspCodeInfo;

/**
 * Response Constant
 *
 * @Author: Heiffeng
 * @Date: 2021/12/1 10:45
 */
public class RC{

    // # 参数类错误
    public final static RspCodeInfo PARAMS_IS_NULL = new RspCodeInfo("CM001","参数为空");
    public final static RspCodeInfo EXISTS = new RspCodeInfo("CM002","");
    public final static RspCodeInfo NOT_EXISTS = new RspCodeInfo("CM003","");
    public final static RspCodeInfo FAILED_VALIDATION = new RspCodeInfo("CM004","无法通过验证");

    // # 数据库类错误
    public final static RspCodeInfo DB_CURD_ERROR = new RspCodeInfo("CM101","数据库CURD错误");
    public final static RspCodeInfo DB_QUERY_RESULT_EMPTY = new RspCodeInfo("CM102","查询结果为空");

    // # 开发类错误
    public final static RspCodeInfo DATA_IS_NULL = new RspCodeInfo("CM902","返回数据(DATA)为空");
    public final static RspCodeInfo CODE_UN_IMPLEMENTS = new RspCodeInfo("CM901","业务代码暂未实现");

}
