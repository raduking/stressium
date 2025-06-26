package org.stressium;

public abstract class AbstractLoopPerformanceService<T extends AbstractLoopPerformanceService.Settings> extends AbstractPerformanceService<T> {

	public static final int DEFAULT_ITERATIONS = 100_000;

	@Override
	public void run(final T customSettings) {
		setUp(customSettings);
		for (int i = 0; i < customSettings.getIterations(); ++i) {
			runStep();
		}
		tearDown();
	}

	public void setUp(final T customSettings) {
		// empty
	}

	public Object runStep() {
		return null;
	}

	public void tearDown() {
		// empty
	}

	public static class Settings {

		private int iterations = DEFAULT_ITERATIONS;

		public int getIterations() {
			return iterations;
		}

		public void setIterations(final int iterations) {
			this.iterations = iterations;
		}

	}

}
