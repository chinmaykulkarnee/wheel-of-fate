package wheeloffate.rule_engine;

import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

public class MaxShiftsInABatchRule  implements Rule {

    @Override
    public boolean match(Engineer engineer, Schedule schedule) {
        return !engineer.isAllocatedMaxNumberOfTimes(schedule.getMaximumShiftsForEngineersInABatch());
    }
}
