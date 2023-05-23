package site.achun.updown.app.service.module.transfer;

import site.achun.file.client.module.file.response.InitFileInfoResponse;

public interface FileTransferStrategy {

    /**
     * 匹配转换器
     * @param file
     * @return
     */
    boolean match(InitFileInfoResponse file);

    /**
     * 获取转换类型
     * @return
     */
    TransferType transferType();

    /**
     * 转换工作
     * @param file
     */
    void handler(InitFileInfoResponse file);
}
