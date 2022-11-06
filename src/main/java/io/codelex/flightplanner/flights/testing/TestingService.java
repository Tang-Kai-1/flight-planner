package io.codelex.flightplanner.flights.testing;

import org.springframework.stereotype.Service;

@Service
public class TestingService {

    private TestingRepository testingRepository;

    public TestingService(TestingRepository testingRepository) {
        this.testingRepository = testingRepository;
    }

    public void clearFlights(){
        testingRepository.clearFlights();
    }
}
