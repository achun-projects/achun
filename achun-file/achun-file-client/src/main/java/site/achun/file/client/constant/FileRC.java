package site.achun.file.client.constant;


import site.achun.support.api.response.RC;
import site.achun.support.api.response.RspCodeInfo;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/11/30 17:05
 **/
public class FileRC extends RC {


    public final static RspCodeInfo BUCKET_NOT_EXISTS = new RspCodeInfo("FI001","Bucket不存在");
    public final static RspCodeInfo FILE_NOT_EXISTS = new RspCodeInfo("FI002","File不存在");

}
