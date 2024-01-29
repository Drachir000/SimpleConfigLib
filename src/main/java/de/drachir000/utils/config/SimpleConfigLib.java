package de.drachir000.utils.config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * SimpleConfigLib is a class that provides static utility methods for working with configurations.
 */
public class SimpleConfigLib {
	
	/**
	 * Creates an empty configuration.
	 *
	 * @return an empty {@link Configuration} object
	 */
	public static Configuration emptyConfiguration() {
		return new Configuration(new JSONObject());
	}
	
	/**
	 * Constructs a {@link Configuration} object from a {@link JSONObject}.
	 * Note that modifying the {@link JSONObject} <b>will</b> impact the {@link Configuration} and vice versa.
	 *
	 * @param jsonObject the {@link JSONObject} representing the {@link Configuration}
	 * @return a {@link Configuration} object created from the {@link JSONObject}
	 */
	public static Configuration configurationFromJSONObject(JSONObject jsonObject) {
		return new Configuration(jsonObject);
	}
	
	/**
	 * Constructs a {@link Configuration} object from a JSON string.
	 *
	 * @param source the JSON string representing the configuration
	 * @return a {@link Configuration} object created from the JSON string
	 * @throws JSONException If there is a syntax error in the source string or a duplicated key.
	 */
	public static Configuration configurationFromString(String source) throws JSONException {
		return new Configuration(new JSONObject(source));
	}
	
}
