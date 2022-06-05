package com.afterdrawing.backendapi.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// created by @author Maksim Ivanov on 18/03/2020
// @project after-drawing
// @description Cucumber runner test


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"

)
public class CucumberRunnerTest  {
 }

