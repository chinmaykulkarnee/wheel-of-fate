package wheeloffate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Shift {
    private int numberInDay;

    @Getter
    private Engineer engineer;

    public Engineer deAllocateEngineer() {
        if (engineer == null)
            return null;
        Engineer engineer = this.engineer;
        this.engineer.reduceAllocationCount();
        this.engineer = null;
        return engineer;
    }

    public boolean isThisEngineerAllocated(Engineer engineer) {
        return this.engineer != null && this.engineer.equals(engineer);
    }

    @Override
    public String toString() {
        return "Shift{" +
                numberInDay +
                ", engineer=" + engineer +
                '}';
    }
}
