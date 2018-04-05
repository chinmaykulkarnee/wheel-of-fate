package wheeloffate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wheeloffate.model.Schedule;
import wheeloffate.service.Scheduler;

@RestController
public class ScheduleController {

    private final Scheduler scheduler;

    public ScheduleController(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public ResponseEntity<ScheduleResponse> generateScheduleFor(ScheduleRequest request) {
        if (request.validate()) {
            long start = System.currentTimeMillis();
            Schedule schedule = scheduler.generate(request.getBatchSizeInDays(), request.getAvailableEngineers(), request.getShiftsPerDay(), request.getMaximumShiftsForEngineersInABatch());
            long end = System.currentTimeMillis();
            return new ResponseEntity<>(schedule.buildResponse(end - start), HttpStatus.OK);
        } else
            return new ResponseEntity<>(ScheduleResponse.errorResponse(), HttpStatus.BAD_REQUEST);
    }
}
