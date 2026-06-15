package helper

public class ParallelExecutor {
	
	
	static void runAllTags() {

		String tags=ScenarioSplitter.extractTags("Include/features/Create New User.feature")

		println("✅ Found tags: " + tags)

		tags.each { tag ->

			def cmd = [
				"cmd", "/c",
				"start katalon -noSplash -runMode=console " +
				"-projectPath=\"${System.getProperty("user.dir")}\\BDD Automation API Test Project.prj\" " +
				"-testSuitePath=\"Test Suites/BDD\" " +
				"-Dcucumber.filter.tags=\"${tag}\""
			]

			println("🚀 Running tag: " + tag)

			cmd.execute()
		}
	}

}
