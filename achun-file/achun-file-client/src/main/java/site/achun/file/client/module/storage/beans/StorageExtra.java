package site.achun.file.client.module.storage.beans;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageExtra implements Serializable {

    private Env defaultEnv;

    private List<Env> envs;

    @Data
    @ToString
    public static class Env implements Serializable{
        private static final long serialVersionUID = 8698497970922611836L;
        private String code;
        private String host;
        private String path;
    }
}
