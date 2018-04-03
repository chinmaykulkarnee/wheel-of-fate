package wheeloffate.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EngineerTest {

    @Test
    public void shouldReturnAllocationCountAs1WhenIncrementedFrom0() {
        Engineer engineer = new Engineer("Engineer1", 0);

        int updatedCount = engineer.incrementAllocationCount();

        assertThat(updatedCount, is(1));
    }

    @Test
    public void shouldReturnAllocationCountAs0WhenReducedFrom1() {
        Engineer engineer = new Engineer("Engineer1", 1);

        int updatedCount = engineer.reduceAllocationCount();

        assertThat(updatedCount, is(0));
    }

    @Test
    public void shouldReturnAllocationCountAs0WhenReducedFrom0() {
        Engineer engineer = new Engineer("Engineer1", 0);

        int updatedCount = engineer.reduceAllocationCount();

        assertThat(updatedCount, is(0));
    }

    @Test
    public void shouldReturnTrueIfEngineersAllocationCountIsEqualToMaxCount() {
        Engineer engineer = new Engineer("Engineer1", 2);

        boolean isAllocatedMaxTimes = engineer.isAllocatedMaxNumberOfTimes(2);

        assertTrue(isAllocatedMaxTimes);
    }

    @Test
    public void shouldReturnTrueIfEngineersAllocationCountIsGreaterThanToMaxCount() {
        Engineer engineer = new Engineer("Engineer1", 3);

        boolean isAllocatedMaxTimes = engineer.isAllocatedMaxNumberOfTimes(2);

        assertTrue(isAllocatedMaxTimes);
    }

    @Test
    public void shouldReturnFalseIfEngineersAllocationCountIsLessThanToMaxCount() {
        Engineer engineer = new Engineer("Engineer1", 1);

        boolean isAllocatedMaxTimes = engineer.isAllocatedMaxNumberOfTimes(2);

        assertFalse(isAllocatedMaxTimes);
    }
}