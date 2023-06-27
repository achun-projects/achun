package site.achun.gallery.app.service.rules;

import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.service.rules.beans.BaseRule;
import site.achun.gallery.app.service.rules.beans.RuleType;

import java.util.List;

public interface QueryHandler {

    boolean match(RuleType type);

    List<MediaFileResponse> query(String rule);
}
