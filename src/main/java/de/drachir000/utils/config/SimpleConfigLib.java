package de.drachir000.utils.config;

import org.json.JSONObject;

public class SimpleConfigLib {
	
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
	
}
