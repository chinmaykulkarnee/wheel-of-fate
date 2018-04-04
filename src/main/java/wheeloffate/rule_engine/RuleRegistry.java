package wheeloffate.rule_engine;

import org.springframework.stereotype.Service;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

import java.util.LinkedList;
import java.util.List;

@Service
public class RuleRegistry {
    private List<Rule> rules;

    private AtMostOneShiftInADayRule atMostOneShiftInADayRule;
    private MaxShiftsInABatchRule maxShiftsInABatchRule;
    private NoShiftsOnConsecutiveDaysRule noShiftsOnConsecutiveDaysRul;

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
