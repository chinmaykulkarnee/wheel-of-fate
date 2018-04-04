package wheeloffate.service;

import org.junit.Test;
import wheeloffate.exception.UnableToFindEngineerException;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;
import wheeloffate.rule_engine.RuleRegistry;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EngineersPoolTest {

    @Test
    public void shouldReturnRandomEngineerFromPoolWhichMatchesAllTheRules() throws UnableToFindEngineerException {
        RuleRegistry mockRuleRegistry = mock(RuleRegistry.class);
        EngineersPool pool = new EngineersPool(mockRuleRegistry, 2);
        when(mockRuleRegistry.matchAll(any(), any())).thenReturn(true);

        Engineer engineer = pool.getValid(new Schedule(new ArrayList<>(), 1, 1));

        assertThat(engineer.getAllocationCount(), is(1));
    }

    @Test(expected = UnableToFindEngineerException.class)
    public void shouldThrowExceptionIfNoEngineerFoundWhichMatchesAllThERules() throws UnableToFindEngineerException {
        RuleRegistry mockRuleRegistry = mock(RuleRegistry.class);
        EngineersPool pool = new EngineersPool(mockRuleRegistry, 2);
        when(mockRuleRegistry.matchAll(any(), any())).thenReturn(false);

        pool.getValid(new Schedule(new ArrayList<>(), 1, 1));
    }
}