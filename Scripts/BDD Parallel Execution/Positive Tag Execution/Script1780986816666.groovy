import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import stepdefination.RunCucumberTest

System.setProperty("cucumber.options", "--tags @positive")
CucumberKW.runWithCucumberRunner(RunCucumberTest.class)
