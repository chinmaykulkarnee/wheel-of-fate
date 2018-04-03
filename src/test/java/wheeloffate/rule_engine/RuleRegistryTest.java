package wheeloffate.rule_engine;

import org.junit.Before;
import org.junit.Test;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleRegistryTest {

    private AtMostOneShiftInADayRule mockAtMostOneShiftInADayRule;
    private MaxShiftsInABatchRule mockMaxShiftsInABatchRule;
    private NoShiftsOnConsecutiveDaysRule mockNoShiftsOnConsecutiveDaysRule;
    private RuleRegistry ruleRegistry;

    @Before
    public void setUp() {
        mockAtMostOneShiftInADayRule = mock(AtMostOneShiftInADayRule.class);
        mockMaxShiftsInABatchRule = mock(MaxShiftsInABatchRule.class);
        mockNoShiftsOnConsecutiveDaysRule = mock(NoShiftsOnConsecutiveDaysRule.class);
        ruleRegistry = new RuleRegistry(mockAtMostOneShiftInADayRule, mockMaxShiftsInABatchRule, mockNoShiftsOnConsecutiveDaysRule);

    }

    @Test
    public void shouldReturnTrueOnlyIfAllTheRulesMatch() {
        Engineer en1 = new Engineer("en1", 1);
        Schedule schedule = new Schedule(new ArrayList<>(), 2, 2);

        when(mockAtMostOneShiftInADayRule.match(en1, schedule)).thenReturn(true);
        when(mockMaxShiftsInABatchRule.match(en1, schedule)).thenReturn(true);
        when(mockNoShiftsOnConsecutiveDaysRule.match(en1, schedule)).thenReturn(true);

        boolean matchAll = ruleRegistry.matchAll(en1, schedule);

        assertTrue(matchAll);
    }

    @Test
    public void shouldReturnFalseOnlyIfAnyOfTheRulesFailToMatch() {
        Engineer en1 = new Engineer("en1", 1);
        Schedule schedule = new Schedule(new ArrayList<>(), 2, 2);

        when(mockAtMostOneShiftInADayRule.match(en1, schedule)).thenReturn(true);
        when(mockMaxShiftsInABatchRule.match(en1, schedule)).thenReturn(false);
        when(mockNoShiftsOnConsecutiveDaysRule.match(en1, schedule)).thenReturn(true);

        boolean matchAll = ruleRegistry.matchAll(en1, schedule);

        assertFalse(matchAll);
    }
}