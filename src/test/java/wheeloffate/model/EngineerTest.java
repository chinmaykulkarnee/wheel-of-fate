package wheeloffate.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}