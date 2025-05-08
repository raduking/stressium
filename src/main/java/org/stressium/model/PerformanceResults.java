package org.stressium.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apiphany.json.JsonBuilder;

public class PerformanceResults {

	private List<PerformanceResult> results = new ArrayList<>();

	@Override
	public String toString() {
		return JsonBuilder.toJson(this);
	}

	public List<PerformanceResult> getResults() {
		return results;
	}

	public void setResults(final List<PerformanceResult> results) {
		this.results = results;
	}

	public void addResult(final PerformanceResult result) {
		this.results.add(result);
	}

	public void addResults(final Collection<PerformanceResult> results) {
		this.results.addAll(results);
	}

}
