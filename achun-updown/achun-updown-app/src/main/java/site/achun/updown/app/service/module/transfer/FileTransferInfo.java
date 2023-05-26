package site.achun.updown.app.service.module.transfer;

import lombok.Data;
import site.achun.file.client.module.storage.response.StorageResponse;

import java.io.File;

@Data
public class FileTransferInfo {

    private File file;
    private String fileCode;
    private String inStoragePath;
    private StorageResponse storage;
}
