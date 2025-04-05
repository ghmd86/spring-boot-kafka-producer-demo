package com.practice.gtm.spring_boot_kafka_producer_demo.controller;

import com.practice.gtm.spring_boot_kafka_producer_demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class UpdateLocationController {
    @Autowired
    private LocationService locationService;

    @PutMapping
    public String updateLocation() throws InterruptedException {
        int range = 100;
        while (range > 0) {
            locationService.updateLocation(Math.random() + ", " + Math.random());
            range--;
        }
        locationService.updateLocation("Completed");
        return "Updated Successfully";
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<String> handleInterrupted(InterruptedException ex) {
        Thread.currentThread().interrupt();
        return ResponseEntity.internalServerError().body("Handled Interrupted Exception:" + ex.getMessage());

    }
}
