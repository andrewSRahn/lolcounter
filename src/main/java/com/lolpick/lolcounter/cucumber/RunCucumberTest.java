package com.lolpick.lolcounter.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"},
		features = {
				"src/test/champions.feature",
				"src/test/lanes.feature"
		},
		glue = {"com.lolpick.lolcounter.glue"})
public class RunCucumberTest {
}