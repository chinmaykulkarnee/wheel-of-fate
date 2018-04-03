package wheeloffate.model;

import javaslang.control.Option;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
public class Schedule {
    private List<Day> days;
    private int shiftsPerDay;
    private int maximumShiftsForEngineersInABatch;

    public boolean isComplete(int batchSizeInDays) {
        return days.size() == batchSizeInDays &&
                days.get(batchSizeInDays - 1).getShifts().size() == shiftsPerDay;
    }

    public List<Day> addDay(Day newDay) {
        days.add(newDay);
        return days;
    }

    public List<Day> removeCurrentDay() {
        if (getSize() > 0) {
            Option<Day> mayBeLatestDay = getCurrentDay();
            mayBeLatestDay.forEach(Day::removeShifts);
            days.remove(getSize() - 1);
        }
        return days;
    }

    public Option<Day> getCurrentDay() {
        if (getSize() > 0)
            return Option.some(days.get(getSize() - 1));
        return Option.none();
    }

    public Option<Day> getYesterday() {
        if (getSize() > 1)
            return Option.some(days.get(getSize() - 2));
        return Option.none();
    }

    public int getSize() {
        return days.size();
    }
}
