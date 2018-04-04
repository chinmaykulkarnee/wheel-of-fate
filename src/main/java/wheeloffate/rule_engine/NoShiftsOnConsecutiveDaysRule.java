package wheeloffate.rule_engine;

import javaslang.control.Option;
import org.springframework.stereotype.Component;
import wheeloffate.model.Day;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

@Component
public class NoShiftsOnConsecutiveDaysRule implements Rule {

    @Override
    public boolean match(Engineer engineer, Schedule schedule) {

        Option<Day> yesterday = schedule.getYesterday();
        return yesterday
                .map(day -> !day.isThisEngineerAllocatedInAnyOfTheShifts(engineer))
                .getOrElse(true);
    }
}
