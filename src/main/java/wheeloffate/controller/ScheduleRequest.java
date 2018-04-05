package wheeloffate.controller;

import lombok.Data;

@Data
class ScheduleRequest {
    private int batchSizeInDays;
    private int availableEngineers;
    private int shiftsPerDay;
    private int maximumShiftsForEngineersInABatch;

    public boolean validate() {
        return batchSizeInDays != 0 &&
                availableEngineers != 0 &&
                shiftsPerDay != 0 &&
                maximumShiftsForEngineersInABatch != 0;
    }
}
