import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

import stepdefination.RunCucumberTest

System.setProperty("cucumber.options", "--tags @negative")
CucumberKW.runWithCucumberRunner(RunCucumberTest.class)
