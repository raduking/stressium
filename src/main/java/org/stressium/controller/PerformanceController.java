package org.stressium.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stressium.PerformanceRunner;
import org.stressium.model.PerformanceResults;
import org.stressium.model.PerformanceSettings;

@RestController
@RequestMapping(path = { PerformanceController.API_STRESSIUM })
public class PerformanceController {

	public static final String API_STRESSIUM = "/api/stressium";

	private final PerformanceRunner performanceRunner;

	public PerformanceController(final PerformanceRunner performanceRunner) {
		this.performanceRunner = performanceRunner;
	}

	@PostMapping
	public ResponseEntity<PerformanceResults> runPerformance(@RequestBody final PerformanceSettings performanceSettings) {
		PerformanceResults results = performanceRunner.run(performanceSettings);
		return ResponseEntity.ok(results);
	}

}
