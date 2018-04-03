package wheeloffate.rule_engine;

import org.junit.Test;
import wheeloffate.model.Day;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;
import wheeloffate.model.Shift;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoShiftsOnConsecutiveDaysRuleTest {

    private NoShiftsOnConsecutiveDaysRule noShiftsOnConsecutiveDaysRule = new NoShiftsOnConsecutiveDaysRule();

    @Test
    public void shouldReturnTrueIfCurrentEngineerIsNotAllocatedToAnyShiftsYesterdayInSchedule() {
        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Shift> shifts1 = new ArrayList<>();
        shifts1.add(new Shift(0, new Engineer("en1", 1)));
        Day day1 = new Day(shifts1, "day1");
        days.add(day1);

        ArrayList<Shift> shifts2 = new ArrayList<>();
        shifts2.add(new Shift(0, new Engineer("en2", 1)));
        Day day2 = new Day(shifts2, "day2");
        days.add(day2);

        Schedule schedule =  new Schedule(days, 2, 1);

        Engineer engineer3 = new Engineer("en3", 0);
        boolean match = noShiftsOnConsecutiveDaysRule.match(engineer3, schedule);

        assertTrue(match);
    }

    @Test
    public void shouldReturnTrueIfScheduleHasNoYesterday() {
        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Shift> shifts1 = new ArrayList<>();
        shifts1.add(new Shift(0, new Engineer("engineer1", 1)));
        Day day1 = new Day(shifts1, "day1");
        days.add(day1);

        Schedule schedule =  new Schedule(days, 2, 2);

        Engineer engineer2 = new Engineer("engineer2", 0);
        boolean match = noShiftsOnConsecutiveDaysRule.match(engineer2, schedule);

        assertTrue(match);
    }

    @Test
    public void shouldReturnFalseIfCurrentEngineerIsNotAllocatedToOneOfTheShiftsYesterdayInSchedule() {
        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Shift> shifts1 = new ArrayList<>();
        Engineer en1 = new Engineer("en1", 1);
        shifts1.add(new Shift(0, en1));
        Day day1 = new Day(shifts1, "day1");
        days.add(day1);

        ArrayList<Shift> shifts2 = new ArrayList<>();
        shifts2.add(new Shift(0, new Engineer("en2", 1)));
        Day day2 = new Day(shifts2, "day2");
        days.add(day2);

        Schedule schedule =  new Schedule(days, 2, 1);

        boolean match = noShiftsOnConsecutiveDaysRule.match(en1, schedule);

        assertFalse(match);
    }
}