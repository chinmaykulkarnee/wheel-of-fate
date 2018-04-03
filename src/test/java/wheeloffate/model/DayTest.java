package wheeloffate.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class DayTest {

    @Test
    public void shouldAddNewShiftToTheDay() {
        Day day = new Day(new ArrayList<>(), "day1");

        List<Shift> shifts = day.addShift(new Shift(0, new Engineer("en1", 1)));

        assertThat(shifts.size(), is(1));
    }

    @Test
    public void shouldRemoveAllShiftsFromDayAndRemoveEngineerFromShiftAndReduceAllocationCountOfEngineerIfExists() {
        Engineer engineer = new Engineer("en1", 1);
        Shift shift = new Shift(0, engineer);
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(shift);
        Day day = new Day(shifts, "day1");

        List<Shift> shiftsAfterRemoval = day.removeShifts();

        assertThat(shiftsAfterRemoval.size(), is(0));
        assertThat(shift.getEngineer(), is(nullValue()));
        assertThat(engineer.getAllocationCount(), is(0));
    }

    @Test
    public void shouldReturnTrueIfGivenEngineerIsAllocatedInAnyOfTheShifts() {
        Engineer engineer = new Engineer("en1", 1);
        Shift shift = new Shift(0, engineer);
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(shift);
        Day day = new Day(shifts, "day1");

        boolean isAllocated = day.isThisEngineerAllocatedInAnyOfTheShifts(engineer);

        assertTrue(isAllocated);

    }

    @Test
    public void shouldReturnFalseIfGivenEngineerIsNotAllocatedInAnyOfTheShifts() {
        Engineer engineer = new Engineer("en1", 1);
        Shift shift = new Shift(0, null);
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(shift);
        Day day = new Day(shifts, "day1");

        boolean isAllocated = day.isThisEngineerAllocatedInAnyOfTheShifts(engineer);

        assertFalse(isAllocated);

    }
}