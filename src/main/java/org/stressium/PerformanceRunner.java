package org.stressium;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.stressium.model.PerformanceResults;
import org.stressium.model.PerformanceSettings;

/**
 * This class handles which performance service to run.
 *
 * @author Radu Sebastian LAZIN
 */
@Service
public class PerformanceRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceRunner.class);

	private final List<PerformanceService> performanceServices;

	public PerformanceRunner(final List<PerformanceService> performanceServices) {
		LOGGER.info("Loading: {}", getClass().getSimpleName());
		this.performanceServices = performanceServices;
		this.performanceServices.stream()
				.forEach(performanceService -> LOGGER.info("Loaded performance service: {}", performanceService.name()));
		LOGGER.info("Loaded: {}", getClass().getSimpleName());
	}

	public PerformanceResults run() {
		return run(new PerformanceSettings());
	}

	public PerformanceResults run(final PerformanceSettings performanceSettings) {
		PerformanceResults results = new PerformanceResults();
		Set<String> servicesToRun = performanceSettings.getServices().keySet();
		performanceServices.stream()
				.filter(svc -> servicesToRun.contains(svc.getClass().getSimpleName()))
				.forEach(service -> results.addResult(service.run(performanceSettings)));
		return results;
	}
}
