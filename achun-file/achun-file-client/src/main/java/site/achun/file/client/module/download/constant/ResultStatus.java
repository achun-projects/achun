package site.achun.file.client.module.download.constant;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/1 0:15
 */
public class ResultStatus {

    // 成功下载
    public final static Integer SUCCESS = 100;
    // 成功下载，并覆盖文件
    public final static Integer SUCCESS_OVERRIDE = 101;
    // 因文件已存在，且不覆盖，被认为是成功执行
    public final static Integer SUCCESS_NO_OVERRIDE = 102;


    public final static Integer ERROR = 200;



}
