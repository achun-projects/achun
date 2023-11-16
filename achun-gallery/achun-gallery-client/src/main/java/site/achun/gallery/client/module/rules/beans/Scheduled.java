package site.achun.gallery.client.module.rules.beans;

import lombok.Data;

@Data
public class Scheduled {
    private String name;
    private String cron;
    private String ruleCode;
    private Integer order;
}
