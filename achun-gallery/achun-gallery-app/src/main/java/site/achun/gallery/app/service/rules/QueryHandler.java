package site.achun.gallery.app.service.rules;

import site.achun.gallery.client.module.rules.beans.RuleType;

import java.util.List;

public interface QueryHandler {

    boolean match(RuleType type);

    List<String> query(String rule);
}
