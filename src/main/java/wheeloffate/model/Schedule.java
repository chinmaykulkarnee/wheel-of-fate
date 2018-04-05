package wheeloffate.model;

import javaslang.control.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import wheeloffate.controller.ScheduleResponse;

import java.util.List;

@ToString
@AllArgsConstructor
public class Schedule {
    private List<Day> days;
    @Getter
    private int shiftsPerDay;
    @Getter
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

    public ScheduleResponse buildResponse(long timeTaken) {
        ScheduleResponse response = new ScheduleResponse();
        response.setDays(days);
        response.setSize(days.size());
        response.setMessage("Time taken to build the schedule the request is : " + timeTaken + "ms");
        return response;
    }
}
