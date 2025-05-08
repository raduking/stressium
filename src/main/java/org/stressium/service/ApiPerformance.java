package org.stressium.service;

import org.apiphany.ApiClient;
import org.apiphany.ApiRequest;
import org.apiphany.client.ExchangeClient;
import org.stressium.AbstractPerformanceService;

public class ApiPerformance extends AbstractPerformanceService<ApiPerformance.Settings> {

	private final ApiPerformanceClient apiClient;

	public ApiPerformance(final ExchangeClient exchangeClient) {
		this.apiClient = new ApiPerformanceClient(exchangeClient);
	}

	@Override
	public void run(final Settings customSettings) {
		apiClient.exchange(customSettings);
	}

	@Override
	public Class<Settings> getCustomSettingsClass() {
		return Settings.class;
	}

	public static class ApiPerformanceClient extends ApiClient {

		protected ApiPerformanceClient(final ExchangeClient exchangeClient) {
			super(exchangeClient);
		}

		public String exchange(final Settings settings) {
			return client()
					.apiRequest(settings)
					.retrieve(String.class)
					.orRethrow();
		}

	}

	public static class Settings extends ApiRequest<Object> {
		// empty
	}

}
