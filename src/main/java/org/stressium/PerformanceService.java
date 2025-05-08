package org.stressium;

import java.time.Duration;
import java.time.Instant;

import org.apiphany.lang.Holder;
import org.apiphany.lang.Temporals;
import org.stressium.model.PerformanceResult;
import org.stressium.model.PerformanceSettings;

/**
 * Performance service interface.
 *
 * @author Radu Sebastian LAZIN
 */
public interface PerformanceService {

	/**
	 * Runs the performance.
	 *
	 * @param performanceSettings settings for the current test
	 */
	PerformanceResult run(PerformanceSettings performanceSettings);

	/**
	 * Runs the performance with defaults.
	 */
	default PerformanceResult run() {
		return run(new PerformanceSettings());
	}

	/**
	 * Returns the name of the service.
	 *
	 * @return the name of the service
	 */
	default String name() {
		return getClass().getSimpleName();
	}

	/**
	 * Sets the time it takes to run the given runnable in the provided holder.
	 *
	 * @param runnable code to time
	 * @param timeResult holds the time it took to run the runnable
	 */
	public static void time(final Runnable runnable, final Holder<Double> timeResult) {
		Instant begin = Instant.now();
		try {
			runnable.run();
		} finally {
			Instant end = Instant.now();
			timeResult.setValue(Temporals.toDouble(Duration.between(begin, end)));
		}
	}
}
