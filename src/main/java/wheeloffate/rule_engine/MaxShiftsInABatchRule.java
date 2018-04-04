package wheeloffate.rule_engine;

import org.springframework.stereotype.Component;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

@Component
public class MaxShiftsInABatchRule  implements Rule {

    @Override
    public boolean match(Engineer engineer, Schedule schedule) {
        return !engineer.isAllocatedMaxNumberOfTimes(schedule.getMaximumShiftsForEngineersInABatch());
    }
}
