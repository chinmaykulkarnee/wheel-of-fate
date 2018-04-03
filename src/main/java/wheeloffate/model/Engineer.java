package wheeloffate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
public class Engineer {
    private String name;
    private int allocationCount;

    public int incrementAllocationCount() {
        allocationCount += 1;
        return allocationCount;
    }

    public int reduceAllocationCount() {
        if (allocationCount > 0)
            allocationCount -= 1;
        return allocationCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;
        Engineer engineer = (Engineer) o;
        return allocationCount == engineer.allocationCount &&
                Objects.equals(name, engineer.name);
    }

    public boolean isAllocatedMaxNumberOfTimes(int maximumShiftsForEngineersInABatch) {
        return getAllocationCount() >= maximumShiftsForEngineersInABatch;
    }
}
