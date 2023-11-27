/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports/report.html"})
public class RunCucumberTest {

}


