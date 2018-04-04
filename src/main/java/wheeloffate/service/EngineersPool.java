package wheeloffate.service;

import javaslang.collection.List;
import org.springframework.stereotype.Service;
import wheeloffate.exception.UnableToFindEngineerException;
import wheeloffate.model.Engineer;
import wheeloffate.model.Schedule;
import wheeloffate.rule_engine.RuleRegistry;

import java.util.Random;

@Service
public class EngineersPool {

    private final RuleRegistry ruleRegistry;
    private final Random random = new Random();
    private List<Engineer> pool;

    EngineersPool(RuleRegistry ruleRegistry) {
        this.ruleRegistry = ruleRegistry;
    }

    public EngineersPool build( int availableEngineers) {
        pool = List.rangeClosed(1, availableEngineers)
                .map(index -> new Engineer("Engineer" + index, 0));
        return this;
    }

    public Engineer getValid(Schedule schedule) throws UnableToFindEngineerException {

        for (int i = 0; i < (pool.size() * schedule.getMaximumShiftsForEngineersInABatch()); i++) {
            Engineer engineer = pool.get(random.nextInt((pool.size() - 1) + 1));
            if (ruleRegistry.matchAll(engineer, schedule)) {
                engineer.incrementAllocationCount();
                return engineer;
            }
        }
        throw new UnableToFindEngineerException();
    }
}
