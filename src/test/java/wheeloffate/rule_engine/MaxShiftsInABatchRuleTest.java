package wheeloffate.rule_engine;

import org.junit.Test;
import wheeloffate.model.Day;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;
import wheeloffate.model.Shift;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaxShiftsInABatchRuleTest {

    private MaxShiftsInABatchRule maxShiftsInABatchRule = new MaxShiftsInABatchRule();

    @Test
    public void shouldReturnTrueIfEngineerIsNotAllocatedMaxNumberTimes() {

        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Shift> shifts1 = new ArrayList<>();
        Engineer engineer1 = new Engineer("engineer1", 1);
        shifts1.add(new Shift(0, engineer1));
        Day day1 = new Day(shifts1, "day1");
        days.add(day1);

        Schedule schedule =  new Schedule(days, 2, 2);

        boolean match = maxShiftsInABatchRule.match(engineer1, schedule);

        assertTrue(match);
    }

    @Test
    public void shouldReturnFalseIfEngineerIsAllocatedMaxNumberTimes() {

        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Shift> shifts1 = new ArrayList<>();
        Engineer engineer1 = new Engineer("engineer1", 1);
        shifts1.add(new Shift(0, engineer1));
        Day day1 = new Day(shifts1, "day1");
        days.add(day1);

        Schedule schedule =  new Schedule(days, 2, 1);

        boolean match = maxShiftsInABatchRule.match(engineer1, schedule);

        assertFalse(match);
    }
}