package site.achun.gallery.client.module.pictures.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Timeline implements Serializable {
    private LocalDate time;
    private Integer count;
    private List<Photo> photos;
}
