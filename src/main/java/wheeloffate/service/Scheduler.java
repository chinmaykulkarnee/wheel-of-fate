package wheeloffate.service;

import org.springframework.stereotype.Service;
import wheeloffate.exception.UnableToFindEngineerException;
import wheeloffate.model.Day;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;
import wheeloffate.model.Shift;
import wheeloffate.rule_engine.RuleRegistry;

import java.util.ArrayList;

@Service
public class Scheduler {
    private final RuleRegistry ruleRegistry;

    Scheduler(RuleRegistry ruleRegistry) {
        this.ruleRegistry = ruleRegistry;
    }

    public Schedule generate(int batchSizeInDays, int availableEngineers, int shiftsPerDay, int maximumShiftsForEngineersInABatch) {
        EngineersPool engineersPool = new EngineersPool(ruleRegistry);
        engineersPool.build(availableEngineers);
        return generate_recursively(batchSizeInDays,
                engineersPool,
                new Schedule(new ArrayList<>(), shiftsPerDay, maximumShiftsForEngineersInABatch)
        );
    }

    private Schedule generate_recursively(int batchSizeInDays, EngineersPool pool, Schedule schedule) {
        if (schedule.isComplete(batchSizeInDays))
            return schedule;

        try {
            Day day = new Day(new ArrayList<>(), "Day" + (schedule.getSize() + 1));
            schedule.addDay(day);
            for (int i = 1; i <= schedule.getShiftsPerDay(); i++) {
                Engineer engineer = pool.getValid(schedule);
                Shift newShift = new Shift(i, engineer);
                day.addShift(newShift);
            }

        } catch (UnableToFindEngineerException e) {
            for (int i = 0; i < schedule.getShiftsPerDay() + 1; i++) {
                schedule.removeCurrentDay();
            }
        }

        return generate_recursively(batchSizeInDays, pool, schedule);
    }
}
