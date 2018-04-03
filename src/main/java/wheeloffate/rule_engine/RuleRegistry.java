package wheeloffate.rule_engine;

import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

import java.util.LinkedList;
import java.util.List;

public class RuleRegistry {
    private List<Rule> rules;

    RuleRegistry(AtMostOneShiftInADayRule atMostOneShiftInADayRule,
                 MaxShiftsInABatchRule maxShiftsInABatchRule,
                 NoShiftsOnConsecutiveDaysRule noShiftsOnConsecutiveDaysRule) {
        this.rules = new LinkedList<>();
        rules.add(atMostOneShiftInADayRule);
        rules.add(maxShiftsInABatchRule);
        rules.add(noShiftsOnConsecutiveDaysRule);
    }

    public boolean matchAll(Engineer engineer, Schedule schedule) {
        return rules.stream().allMatch(rule -> rule.match(engineer, schedule));
    }
}
