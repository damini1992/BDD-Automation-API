package helper

public class ScenarioContext {

	private static Map<String, Object> scenarioContext = new HashMap<>();

	public static void setContext(Object key, Object value) {
		scenarioContext.put(key.toString(), value);
	}

	public static Object getContext(Object key) {
		return scenarioContext.get(key.toString());
	}
	
	public static void clear() {
		scenarioContext.clear()
	}
	
}
