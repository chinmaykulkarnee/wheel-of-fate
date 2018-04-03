package wheeloffate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class Day {
    private List<Shift> shifts;
    private String name;

    public List<Shift> addShift(Shift newShift) {
        shifts.add(newShift);
        return shifts;
    }

    public List<Shift> removeShifts() {
        shifts.forEach(Shift::deAllocateEngineer);
        shifts = new ArrayList<>();
        return shifts;
    }

    public boolean isThisEngineerAllocatedInAnyOfTheShifts(Engineer engineer) {
        return shifts.size() != 0 &&
                shifts.stream()
                        .anyMatch(s -> s.isThisEngineerAllocated(engineer));
    }
}
