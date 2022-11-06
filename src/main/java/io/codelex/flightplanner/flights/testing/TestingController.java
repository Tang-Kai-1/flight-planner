package io.codelex.flightplanner.flights.testing;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testing-api", method = {RequestMethod.PUT})
public class TestingController {

    private TestingService testingService;

    public TestingController(TestingService testingService) {
        this.testingService = testingService;
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearFlights() {
        testingService.clearFlights();
    }
}
