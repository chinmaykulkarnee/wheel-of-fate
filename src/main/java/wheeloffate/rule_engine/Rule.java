package wheeloffate.rule_engine;

import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

public interface Rule {
    boolean match(Engineer engineer, Schedule schedule);
}
