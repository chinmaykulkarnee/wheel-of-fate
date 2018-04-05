package wheeloffate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import wheeloffate.controller.ScheduleResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnSuccessResponseWhenScheduleIsGeneratedSuccessfully() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/schedules")
                .queryParam("batchSizeInDays", 10)
                .queryParam("availableEngineers", 10)
                .queryParam("shiftsPerDay", 2)
                .queryParam("maximumShiftsForEngineersInABatch", 2);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<ScheduleResponse> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ScheduleResponse.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        ScheduleResponse scheduleResponse = responseEntity.getBody();
        System.out.println(scheduleResponse.getMessage());
        assertThat(scheduleResponse.getSize(), is(10));
    }

    @Test
    public void shouldReturnErrorResponseWhenParamsAreInvalid() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/schedules")
                .queryParam("availableEngineers", 10)
                .queryParam("shiftsPerDay", 2)
                .queryParam("maximumShiftsForEngineersInABatch", 2);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<ScheduleResponse> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ScheduleResponse.class);


        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        ScheduleResponse scheduleResponse = responseEntity.getBody();
        assertThat(scheduleResponse.getSize(), is(0));
    }
}