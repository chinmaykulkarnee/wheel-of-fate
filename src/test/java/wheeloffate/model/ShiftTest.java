package wheeloffate.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ShiftTest {

    @Test
    public void shouldDeAllocateEngineerFromGivenShift() {
        Engineer engineer = new Engineer("eng1", 1);
        Shift shift = new Shift(0, engineer);

        Engineer deAllocateEngineer = shift.deAllocateEngineer();

        assertThat(deAllocateEngineer.getName(), is(engineer.getName()));
        assertThat(deAllocateEngineer.getAllocationCount(), is(0));
        assertThat(shift.getEngineer(), is(nullValue()));

    }

    @Test
    public void shouldNotDeAllocateEngineerFromGivenShiftWhenNoEngineerIsAllocatedForIt() {
        Shift shift = new Shift(0, null);

        Engineer deAllocateEngineer = shift.deAllocateEngineer();

        assertThat(deAllocateEngineer, is(nullValue()));
        assertThat(shift.getEngineer(), is(nullValue()));

    }

    @Test
    public void shouldReturnTrueIfGivenEngineerIsAllocatedForThisShift() {
        Engineer engineer = new Engineer("eng1", 1);
        Shift shift = new Shift(0, engineer);

        Engineer eng1 = new Engineer("eng1", 1);
        boolean isAllocated = shift.isThisEngineerAllocated(eng1);

        assertTrue(isAllocated);
    }

    @Test
    public void shouldReturnFalseIfGivenEngineerIsNotAllocatedForThisShift() {
        Engineer engineer = new Engineer("eng1", 1);
        Shift shift = new Shift(0, engineer);

        Engineer eng2 = new Engineer("eng2", 0);
        boolean isAllocated = shift.isThisEngineerAllocated(eng2);

        assertFalse(isAllocated);
    }

    @Test
    public void shouldReturnFalseIfNoEngineerIsAllocatedToThisShift() {
        Engineer engineer = new Engineer("eng1", 1);
        Shift shift = new Shift(0, null);

        boolean isAllocated = shift.isThisEngineerAllocated(engineer);

        assertFalse(isAllocated);
    }
}