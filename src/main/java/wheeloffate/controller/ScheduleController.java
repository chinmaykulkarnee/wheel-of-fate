package wheeloffate.controller;

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
    public ScheduleResponse generateScheduleFor(ScheduleRequest request) {
        Schedule schedule = scheduler.generate(request.getBatchSizeInDays(), request.getAvailableEngineers(), request.getShiftsPerDay(), request.getMaximumShiftsForEngineersInABatch());
        return schedule.buildResponse();
    }
}
