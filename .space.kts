/**
* JetBrains Space Automation
* This Kotlin script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Build and run tests") {
    container(displayName = "Run mvn install", image = "maven:latest") {
        shellScript {
            content = """
	            mvn clean install
            """
        }
    }
}

job("Deploy app to Space") {
    container(displayName = "Run mvn install", image = "maven:latest") {
        shellScript {
            content = """
	            mvn clean verify deploy
            """
        }
    }
}
