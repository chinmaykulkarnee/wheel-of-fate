package wheeloffate.rule_engine;

import javaslang.control.Option;
import org.springframework.stereotype.Component;
import wheeloffate.model.Day;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

@Component
public class AtMostOneShiftInADayRule implements Rule {

    @Override
    public boolean match(Engineer engineer, Schedule schedule) {
        Option<Day> today = schedule.getCurrentDay();
        return today
                .map(day -> !day.isThisEngineerAllocatedInAnyOfTheShifts(engineer))
                .getOrElse(true);

    }
}
