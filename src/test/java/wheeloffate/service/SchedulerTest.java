package wheeloffate.service;

import org.junit.Test;
import wheeloffate.model.Schedule;
import wheeloffate.rule_engine.AtMostOneShiftInADayRule;
import wheeloffate.rule_engine.MaxShiftsInABatchRule;
import wheeloffate.rule_engine.NoShiftsOnConsecutiveDaysRule;
import wheeloffate.rule_engine.RuleRegistry;

import static org.junit.Assert.assertTrue;

public class SchedulerTest {

    @Test
    public void shouldGenerateTheScheduleSuccessfullyForSingleBatchSizeAndSingleEngineer() {
        Scheduler scheduler = new Scheduler(new RuleRegistry(new AtMostOneShiftInADayRule(), new MaxShiftsInABatchRule(), new NoShiftsOnConsecutiveDaysRule()));

        Schedule schedule = scheduler.generate(1, 1, 1, 1);

        assertTrue(schedule.isComplete(1));
    }

    @Test
    public void shouldGenerateTheScheduleSuccessfullyForBatchSize2And2AvailableEngineers() {
        Scheduler scheduler = new Scheduler(new RuleRegistry(new AtMostOneShiftInADayRule(), new MaxShiftsInABatchRule(), new NoShiftsOnConsecutiveDaysRule()));

        Schedule schedule = scheduler.generate(2, 2, 1, 1);

        assertTrue(schedule.isComplete(2));
    }

    @Test
    public void shouldGenerateTheScheduleSuccessfullyForBatchSize3And3AvailableEngineers() {
        Scheduler scheduler = new Scheduler(new RuleRegistry(new AtMostOneShiftInADayRule(), new MaxShiftsInABatchRule(), new NoShiftsOnConsecutiveDaysRule()));

        Schedule schedule = scheduler.generate(3, 3, 1, 1);

        assertTrue(schedule.isComplete(3));
    }

    @Test
    public void shouldGenerateTheScheduleSuccessfullyForBatchSize5And5AvailableEngineersAnd2ShiftsInADay() {
        Scheduler scheduler = new Scheduler(new RuleRegistry(new AtMostOneShiftInADayRule(), new MaxShiftsInABatchRule(), new NoShiftsOnConsecutiveDaysRule()));

        Schedule schedule = scheduler.generate(5, 5, 2, 2);

        assertTrue(schedule.isComplete(5));
    }

    @Test
    public void shouldGenerateTheScheduleSuccessfullyForBatchSize15And15AvailableEngineersAnd3ShiftsInADay() {
        Scheduler scheduler = new Scheduler(new RuleRegistry(new AtMostOneShiftInADayRule(), new MaxShiftsInABatchRule(), new NoShiftsOnConsecutiveDaysRule()));

        Schedule schedule = scheduler.generate(15, 15, 3, 3);

        assertTrue(schedule.isComplete(15));
    }
}