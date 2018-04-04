package wheeloffate.controller;

import lombok.Data;

@Data
public class ScheduleRequest {
    private int batchSizeInDays;
    private int availableEngineers;
    private int shiftsPerDay;
    private int maximumShiftsForEngineersInABatch;
}
