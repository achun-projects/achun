package site.achun.video.client.module.pictures.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimelineResponse implements Serializable {
    private LocalDate time;
    private Integer count;
    private List<PictureResponse> pictures;

}
