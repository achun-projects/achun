package site.achun.video.client.module.video.request;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveVideoRequest implements Serializable {


    private String videoCode;
}
