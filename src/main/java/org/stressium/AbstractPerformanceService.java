package org.stressium;

import java.util.ArrayList;
import java.util.List;

import org.apiphany.lang.Holder;
import org.apiphany.lang.accumulator.DurationAccumulator;
import org.morphix.lang.thread.Threads;
import org.morphix.lang.thread.Threads.ExecutionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stressium.model.PerformanceResult;
import org.stressium.model.PerformanceSettings;

public abstract class AbstractPerformanceService<T> implements PerformanceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPerformanceService.class);

	private final DurationAccumulator durationAccumulator = DurationAccumulator.of();

	public abstract void run(T customSettings);

	public abstract Class<T> getCustomSettingsClass();

	@Override
	public PerformanceResult run(final PerformanceSettings performanceSettings) {
		String serviceName = name();
		LOGGER.info("[BEGIN] {}", serviceName);

		durationAccumulator.clear();

		T settings = performanceSettings.getCustomProperties(serviceName, getCustomSettingsClass());

		List<Runnable> runnables = new ArrayList<>(performanceSettings.getThreadCount());
		for (int i = 0; i < performanceSettings.getThreadCount(); ++i) {
			runnables.add(() -> {
				for (int j = 0; j < performanceSettings.getLoopCount(); ++j) {
					durationAccumulator.accumulate(() -> run(settings));
				}
			});
		}

		Holder<Double> executionTimeHolder = new Holder<>();
		PerformanceService.time(() -> Threads.execute(runnables, ExecutionType.VIRTUAL), executionTimeHolder);

		LOGGER.info("{}", durationAccumulator.buildStatistics());
		LOGGER.info("Total execution time: {}s", executionTimeHolder.getValue());

		LOGGER.info("[END] {}", serviceName);

		return new PerformanceResult(serviceName, durationAccumulator, executionTimeHolder.getValue());
	}

}
