package helper

import java.nio.file.Files
import java.nio.file.Paths
public class ScenarioSplitter {
	
	static Set<String> extractTags(String featureDir) {

		Set<String> tags = new HashSet<>()

		Files.walk(Paths.get(featureDir))
				.filter { it.toString().endsWith(".feature") }
				.forEach { file ->

					file.toFile().eachLine { line ->
						line = line.trim()

						if (line.startsWith("@")) {
							line.split(" ").each { tag ->
								tags.add(tag.trim())
							}
						}
					}
				}

		return tags
	}
}

