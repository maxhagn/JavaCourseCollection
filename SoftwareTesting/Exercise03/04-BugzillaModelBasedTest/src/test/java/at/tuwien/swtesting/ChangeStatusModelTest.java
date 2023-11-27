/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting;

import at.tuwien.swtesting.shared.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import at.tuwien.swtesting.pageobjects.HomePage;
import at.tuwien.swtesting.pageobjects.LoginPage;

import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.VerboseListener;
import nz.ac.waikato.modeljunit.coverage.CoverageMetric;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

public class ChangeStatusModelTest {
	
	private static final DriverManagerType DRIVER_TYPE = DriverManagerType.CHROME;
	
	private static WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	
	private BugzillaAdapter adapter;

	@BeforeAll
	public static void setUpBeforeAll() {
		WebDriverManager.getInstance(DRIVER_TYPE).setup(); 	
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void tearDownAfterAll() {
		driver.close();
		driver.quit();
	}
	
	@BeforeEach
	public void setUp() {
		homePage = HomePage.navigateTo(driver, Constants.BASE_URL);
		loginPage = homePage.gotoLoginPage();
		homePage = loginPage.login(Constants.USERNAME, Constants.PASSWORD);
		adapter = new BugzillaAdapter(driver);
	}

	@AfterEach
	public void tearDown() {
		homePage = HomePage.navigateTo(driver, Constants.BASE_URL);
		homePage = homePage.logout();
	}

	
	@Test
	public void testChangeStatus() {
		StatusModelWithAdapter model = new StatusModelWithAdapter(adapter);
		Tester tester = new GreedyTester(model);
		tester.buildGraph();
		CoverageMetric stateCov = new StateCoverage();
		CoverageMetric transitionCov = new TransitionCoverage();
		tester.addListener(stateCov);
		tester.addListener(transitionCov);
		tester.addListener(new VerboseListener());
		tester.addListener(new StopOnFailureListener());
		
		tester.generate(120);

		tester.getModel().printMessage(stateCov.getName() + ": " + stateCov.toString());
		tester.getModel().printMessage(transitionCov.getName() + ": " + transitionCov.toString());
	}


}
