package site.achun.video.app.service.rules;

import site.achun.video.app.service.rules.beans.RuleType;

import java.util.List;

public interface QueryHandler {

    boolean match(RuleType type);

    List<String> query(String rule);
}
