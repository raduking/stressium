package org.stressium.model;

import org.apiphany.json.JsonBuilder;
import org.apiphany.lang.accumulator.DurationAccumulator;

public class PerformanceResult {

	private String description;

	private long count;

	private Time time;

	public PerformanceResult(final String description) {
		this.description = description;
	}

	public PerformanceResult(final String description, final DurationAccumulator accumulator, final Double totalTime) {
		this.description = description;
		this.count = accumulator.size();
		this.setTime(new Time(accumulator, totalTime));
	}

	@Override
	public String toString() {
		return JsonBuilder.toJson(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public long getCount() {
		return count;
	}

	public void setCount(final long count) {
		this.count = count;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public static class Time {

		private Double total;

		private Double average;
		private Double p95;
		private Double p90;
		private Double max;

		public Time(DurationAccumulator accumulator, final Double total) {
			this.total = total;
			this.average = accumulator.average();
			this.p90 = accumulator.percentile(95);
			this.p95 = accumulator.percentile(90);
			this.max = accumulator.max();
		}

		public Double getTotal() {
			return total;
		}

		public void setTotal(final Double totalTime) {
			this.total = totalTime;
		}

		public Double getAverage() {
			return average;
		}

		public void setAverage(final Double average) {
			this.average = average;
		}

		public Double getP95() {
			return p95;
		}

		public void setP95(final Double p95) {
			this.p95 = p95;
		}

		public Double getP90() {
			return p90;
		}

		public void setP90(final Double p90) {
			this.p90 = p90;
		}

		public Double getMax() {
			return max;
		}

		public void setMax(final Double max) {
			this.max = max;
		}

	}
}
