package org.stressium.model;

import java.util.Collections;
import java.util.Map;

import org.apiphany.json.JsonBuilder;
import org.morphix.lang.function.Consumers;

public class PerformanceSettings {

	public static final int DEFAULT_THREAD_COUNT = 20;

	public static final int DEFAULT_LOOP_COUNT = 5;

	private Integer threadCount = DEFAULT_THREAD_COUNT;

	private Integer loopCount = DEFAULT_LOOP_COUNT;

	private Map<String, Object> services = Collections.emptyMap();

	@Override
	public String toString() {
		return JsonBuilder.toJson(this);
	}

	public Integer getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(final Integer threadsCount) {
		this.threadCount = threadsCount;
	}

	public Integer getLoopCount() {
		return loopCount;
	}

	public void setLoopCount(final Integer loopCount) {
		this.loopCount = loopCount;
	}

	public Map<String, Object> getServices() {
		return services;
	}

	public void setServices(final Map<String, Object> services) {
		this.services = services;
	}

	public <T> T getCustomProperties(final String serviceName, final Class<T> cls) {
		Object custom = getServices().get(serviceName);
		Map<String, Object> properties = JsonBuilder.toMap(custom, Consumers.noConsumer());
		return JsonBuilder.fromMap(properties, cls, Consumers.noConsumer());
	}

}
