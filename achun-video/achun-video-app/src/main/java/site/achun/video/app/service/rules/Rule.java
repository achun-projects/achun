package site.achun.video.app.service.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/7/5 14:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule implements Comparable<Rule> {

    private String name;
    private Integer sort;
    private List<String> cron;
    private Set<String> boards;

    @Override
    public int compareTo(Rule o) {
        if(o == null) return 1;
        if(o.getSort() == this.getSort()) return 0;
        return o.getSort() < this.getSort() ? 1 : -1;
    }
}
