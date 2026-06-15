
//CucumberKW.runFeatureFile('Include/features/Create New User.feature')
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import stepdefination.RunCucumberTest



System.setProperty("cucumber.options", "--tags @postive")
CucumberKW.runWithCucumberRunner(RunCucumberTest.class)
