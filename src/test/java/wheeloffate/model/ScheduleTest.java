package wheeloffate.model;

import javaslang.control.Option;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ScheduleTest {

    @Test
    public void shouldReturnIsCompleteAsTrueWhenScheduleHasAllTheDaysAndShiftsAllocated() {
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(0, new Engineer("en1", 1)));
        shifts.add(new Shift(1, new Engineer("en2", 1)));
        ArrayList<Day> days = new ArrayList<>();
        days.add(new Day(shifts, "day1"));
        Schedule schedule =  new Schedule(days, 2, 1);

        boolean complete = schedule.isComplete(1);

        assertTrue(complete);
    }

    @Test
    public void shouldReturnIsCompleteAsFalseWhenScheduleHasAtLeastOneEmptySlot() {
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(0, new Engineer("en1", 1)));
        ArrayList<Day> days = new ArrayList<>();
        days.add(new Day(shifts, "day1"));
        Schedule schedule =  new Schedule(days, 2, 1);

        boolean complete = schedule.isComplete(1);

        assertFalse(complete);
    }

    @Test
    public void shouldAddDayToGivenSchedule() {
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(0, new Engineer("en1", 1)));
        Schedule schedule =  new Schedule(new ArrayList<>(), 2, 1);

        List<Day> days = schedule.addDay(new Day(shifts, "day1"));

        assertThat(days.size(), is(1));
        assertThat(schedule.getSize(), is(1));
    }

    @Test
    public void shouldRemoveCurrentDayFromScheduleIfPresent() {
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(0, new Engineer("en1", 1)));
        ArrayList<Day> days = new ArrayList<>();
        days.add(new Day(shifts, "day1"));
        Schedule schedule =  new Schedule(days, 2, 1);

        List<Day> daysAfterRemoval = schedule.removeCurrentDay();

        assertThat(daysAfterRemoval.size(), is(0));
        assertThat(schedule.getSize(), is(0));
    }

    @Test
    public void shouldNotChangeScheduleOnRemoveCurrentDayIfCurrentDayIsAbsent() {
        Schedule schedule =  new Schedule(new ArrayList<>(), 2, 1);

        List<Day> daysAfterRemoval = schedule.removeCurrentDay();

        assertThat(daysAfterRemoval.size(), is(0));
        assertThat(schedule.getSize(), is(0));
    }

    @Test
    public void shouldReturnCurrentDayFromScheduleIfPresent() {
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(0, new Engineer("en1", 1)));
        ArrayList<Day> days = new ArrayList<>();
        Day day1 = new Day(shifts, "day1");
        days.add(day1);
        Schedule schedule =  new Schedule(days, 2, 1);

        Option<Day> mayBeCurrentDay = schedule.getCurrentDay();

        assertTrue(mayBeCurrentDay.isDefined());
        assertThat(mayBeCurrentDay.get().getName(), is(day1.getName()));
    }

    @Test
    public void shouldReturnEmptyOptionForCurrentDayFromScheduleIfAbsent() {
        Schedule schedule =  new Schedule(new ArrayList<>(), 2, 1);

        Option<Day> mayBeCurrentDay = schedule.getCurrentDay();

        assertFalse(mayBeCurrentDay.isDefined());
    }


    @Test
    public void shouldReturnYesterdayFromScheduleIfPresent() {
        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Shift> shifts1 = new ArrayList<>();
        shifts1.add(new Shift(0, new Engineer("en1", 1)));
        Day day1 = new Day(shifts1, "day1");
        days.add(day1);
        ArrayList<Shift> shifts2 = new ArrayList<>();
        shifts2.add(new Shift(0, new Engineer("en1", 1)));
        Day day2 = new Day(shifts2, "day2");
        days.add(day2);
        Schedule schedule =  new Schedule(days, 2, 1);

        Option<Day> mayBeYesterday = schedule.getYesterday();

        assertTrue(mayBeYesterday.isDefined());
        assertThat(mayBeYesterday.get().getName(), is(day1.getName()));
    }

    @Test
    public void shouldReturnEmptyOptionForYesterdayFromScheduleIfAbsent() {
        Schedule schedule =  new Schedule(new ArrayList<>(), 2, 1);

        Option<Day> mayBeYesterday = schedule.getCurrentDay();

        assertFalse(mayBeYesterday.isDefined());
    }

    @Test
    public void shouldReturnDaysSizeAsScheduleSize() {
        ArrayList<Day> days = new ArrayList<>();
        ArrayList<Shift> shifts1 = new ArrayList<>();
        shifts1.add(new Shift(0, new Engineer("en1", 1)));
        Day day1 = new Day(shifts1, "day1");
        days.add(day1);
        ArrayList<Shift> shifts2 = new ArrayList<>();
        shifts2.add(new Shift(0, new Engineer("en1", 1)));
        Day day2 = new Day(shifts2, "day2");
        days.add(day2);
        Schedule schedule =  new Schedule(days, 2, 1);

        int size = schedule.getSize();

        assertThat(size, is(2));
    }
}