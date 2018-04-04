package wheeloffate.controller;

import lombok.Data;
import wheeloffate.model.Day;

import java.util.List;

@Data
public class ScheduleResponse {
    private List<Day> days;
    private int size;
}
