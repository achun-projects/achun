package site.achun.file.app.controller.storage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bucket更新")
@Order(2)
@Slf4j
@RestController
@RequiredArgsConstructor
public class StorageUpdateController {
}
