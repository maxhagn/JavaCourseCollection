/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.VerboseListener;
import nz.ac.waikato.modeljunit.coverage.CoverageMetric;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

public class RingBufferModelTest {
	

	@Test
	@DisplayName("Test if all transitions are covered.")
	public void testModelOnly() {
		Tester tester = new GreedyTester(new RingBufferModel());
		tester.buildGraph();
		CoverageMetric stateCov = new StateCoverage();
		CoverageMetric transitionCov = new TransitionCoverage();
		tester.addListener(stateCov);
		tester.addListener(transitionCov);
		tester.addListener(new VerboseListener());
		tester.addListener(new StopOnFailureListener());
		
		tester.generate(25);

		tester.getModel().printMessage(stateCov.getName() + ": " + stateCov.toString());
		tester.getModel().printMessage(transitionCov.getName() + ": " + transitionCov.toString());
	}

	@Test
	@DisplayName("Test the sut by using transition coverage.")
	public void testModelWithAdapter() {
		Tester tester = new GreedyTester(new RingBufferModelWithAdapter());
		tester.buildGraph();
		CoverageMetric stateCov = new StateCoverage();
		CoverageMetric transitionCov = new TransitionCoverage();
		tester.addListener(stateCov);
		tester.addListener(transitionCov);
		tester.addListener(new VerboseListener());
		tester.addListener(new StopOnFailureListener());

		tester.generate(25);

		tester.getModel().printMessage(stateCov.getName() + ": " + stateCov.toString());
		tester.getModel().printMessage(transitionCov.getName() + ": " + transitionCov.toString());
	}
	
}
