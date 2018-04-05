package wheeloffate.controller;

import lombok.Data;
import wheeloffate.model.Day;

import java.util.List;

@Data
public class ScheduleResponse {
    private List<Day> days;
    private int size;
    private String message;

    public static ScheduleResponse errorResponse() {
        ScheduleResponse response = new ScheduleResponse();
        response.setSize(0);
        response.setMessage("Invalid request - mandatory params are not sent or sent with invalid value");
        return response;
    }
}
