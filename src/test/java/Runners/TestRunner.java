package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = "APIs",
plugin = {"pretty",
        "html:target/cucumber-reports/Cucumber.html",
        "json:target/cucumber-reports/Cucumber.json",
        "junit:target/cucumber-reports/Cucumber.xml"} ,
features = "src/main/resources/Features")
public class TestRunner extends AbstractTestNGCucumberTests {
}
