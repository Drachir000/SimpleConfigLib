package de.drachir000.utils.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * The Configuration class represents a configuration object that can be used to retrieve values from a JSON object.
 */
public class Configuration {
	
	JSONObject content;
	
	/**
	 * Create a {@link Configuration} based on a {@link JSONObject}
	 *
	 * @param jsonObject The base {@link JSONObject}
	 */
	public Configuration(JSONObject jsonObject) {
		this.content = jsonObject;
	}
	
	/**
	 * Get the value {@link Object} associated with a key.
	 *
	 * @param key A key string.
	 * @return The {@link Object} associated with the key.
	 * @throws JSONException if the key is not found.
	 * @see Configuration#getOrDefault(String, Object)
	 */
	public Object get(String key) throws JSONException {
		return content.get(key);
	}
	
	/**
	 * Get the {@link String} associated with a key.
	 *
	 * @param key A key string.
	 * @return A {@link String} which is the value.
	 * @throws JSONException if there is no {@link String} value for the key.
	 * @see Configuration#getOrDefault(String, String)
	 */
	public String getString(String key) throws JSONException {
		return content.getString(key);
	}
	
	/**
	 * Get the {@link Integer} value associated with a key.
	 *
	 * @param key A key string.
	 * @return The {@link Integer} value.
	 * @throws JSONException if the key is not found or if the value is not a Number object and cannot be converted to a {@link Integer}.
	 * @see Configuration#getOrDefault(String, int)
	 */
	public int getInt(String key) throws JSONException {
		return content.getInt(key);
	}
	
	/**
	 * Get the {@link Long} value associated with a key.
	 *
	 * @param key A key string.
	 * @return The {@link Long} value.
	 * @throws JSONException if the key is not found or if the value is not a Number object and cannot be converted to a {@link Long}.
	 * @see Configuration#getOrDefault(String, long)
	 */
	public long getLong(String key) throws JSONException {
		return content.getLong(key);
	}
	
	/**
	 * Get the {@link Double} value associated with a key.
	 *
	 * @param key A key string.
	 * @return The {@link Double} value.
	 * @throws JSONException if the key is not found or if the value is not a Number object and cannot be converted to a {@link Double}.
	 * @see Configuration#getOrDefault(String, double)
	 */
	public double getDouble(String key) throws JSONException {
		return content.getDouble(key);
	}
	
	/**
	 * Get the {@link Float} value associated with a key.
	 *
	 * @param key A key string.
	 * @return The {@link Float} value.
	 * @throws JSONException if the key is not found or if the value is not a Number object and cannot be converted to a {@link Float}.
	 * @see Configuration#getOrDefault(String, float)
	 */
	public float getFloat(String key) throws JSONException {
		return content.getFloat(key);
	}
	
	/**
	 * Get the {@link Boolean} value associated with a key.
	 *
	 * @param key A key string.
	 * @return The truth.
	 * @throws JSONException if the key is not found or if the value is not a {@link Boolean} or the {@link String} "true" or "false".
	 * @see Configuration#getOrDefault(String, boolean)
	 */
	public boolean getBoolean(String key) throws JSONException {
		return content.getBoolean(key);
	}
	
	/**
	 * Get the {@link Configuration} value associated with a key.
	 *
	 * @param key A key string.
	 * @return A {@link Configuration} parsed of the {@link JSONObject} stored at the key.
	 * @throws JSONException if the key is not found or if the value is not a {@link JSONObject}.
	 * @see Configuration#getOrDefault(String, Configuration)
	 */
	public Configuration getConfiguration(String key) throws JSONException {
		
		Object object = content.get(key);
		
		if (object instanceof JSONObject) {
			return new Configuration((JSONObject) object);
		} else if (object instanceof String) {
			return new Configuration(new JSONObject((String) object));
		} else {
			return new Configuration(new JSONObject(object.toString()));
		}
		
	}
	
	/**
	 * Get the {@link JSONArray} value associated with a key.
	 *
	 * @param key A key string.
	 * @return A {@link JSONArray} which is the value.
	 * @throws JSONException if the key is not found or if the value is not a {@link JSONArray}.
	 * @see Configuration#getOrDefault(String, JSONArray)
	 */
	public JSONArray getJSONArray(String key) throws JSONException {
		return content.getJSONArray(key);
	}
	
	/**
	 * Get the {@link Enum} value associated with a key.
	 *
	 * @param clazz The type of {@link Enum} to retrieve.
	 * @param key   A key string.
	 * @param <E>   {@link Enum} Type
	 * @return The {@link Enum} value associated with the key
	 * @throws JSONException if the key is not found or if the value cannot be converted to an {@link Enum}.
	 * @see Configuration#getOrDefault(Class, String, Enum)
	 */
	public <E extends Enum<E>> E getEnum(Class<E> clazz, String key) throws JSONException {
		return content.getEnum(clazz, key);
	}
	
	/**
	 * Get the value {@link Object} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Object} associated with the key or the fallback value
	 * @see Configuration#get(String)
	 */
	public Object getOrDefault(String key, Object defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return get(key);
	}
	
	/**
	 * Get the {@link String} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link String} associated with the key or the fallback value
	 * @see Configuration#getString(String)
	 */
	public String getOrDefault(String key, String defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getString(key);
	}
	
	/**
	 * Get the {@link Integer} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Integer} associated with the key or the fallback value
	 * @see Configuration#getInt(String)
	 */
	public int getOrDefault(String key, int defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getInt(key);
	}
	
	/**
	 * Get the {@link Long} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Long} associated with the key or the fallback value
	 * @see Configuration#getLong(String)
	 */
	public long getOrDefault(String key, long defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getLong(key);
	}
	
	/**
	 * Get the {@link Double} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Double} associated with the key or the fallback value
	 * @see Configuration#getDouble(String)
	 */
	public double getOrDefault(String key, double defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getDouble(key);
	}
	
	/**
	 * Get the {@link Float} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Float} associated with the key or the fallback value
	 * @see Configuration#getFloat(String)
	 */
	public float getOrDefault(String key, float defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getFloat(key);
	}
	
	/**
	 * Get the {@link Boolean} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Boolean} associated with the key or the fallback value
	 * @see Configuration#getBoolean(String)
	 */
	public boolean getOrDefault(String key, boolean defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getBoolean(key);
	}
	
	/**
	 * Get the {@link Configuration} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Configuration} associated with the key or the fallback value
	 * @see Configuration#getConfiguration(String)
	 */
	public Configuration getOrDefault(String key, Configuration defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getConfiguration(key);
	}
	
	/**
	 * Get the {@link JSONArray} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link JSONArray} associated with the key or the fallback value
	 * @see Configuration#getJSONArray(String)
	 */
	public JSONArray getOrDefault(String key, JSONArray defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getJSONArray(key);
	}
	
	/**
	 * Get the {@link Enum} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param clazz        The type of {@link Enum} to retrieve.
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @param <E>          {@link Enum} Type
	 * @return The {@link Enum} associated with the key or the fallback value
	 * @see Configuration#getEnum(Class, String)
	 */
	public <E extends Enum<E>> E getOrDefault(Class<E> clazz, String key, E defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		return getEnum(clazz, key);
	}
	
	/**
	 * Save an {@link Object} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Object} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object set(String key, Object value) {
		Object prevValue = null;
		if (content.has(key))
			prevValue = get(key);
		content.put(key, value);
		return prevValue;
	}
	
	/**
	 * Save an {@link String} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link String} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setString(String key, String value) {
		return set(key, value);
	}
	
	/**
	 * Save an {@link Integer} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Integer} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setInt(String key, int value) {
		return set(key, value);
	}
	
	/**
	 * Save an {@link Long} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Long} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setLong(String key, long value) {
		return set(key, value);
	}
	
	/**
	 * Save an {@link Double} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Double} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setDouble(String key, double value) {
		return set(key, value);
	}
	
	/**
	 * Save an {@link Float} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Float} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setFloat(String key, float value) {
		return set(key, value);
	}
	
	/**
	 * Save an {@link Boolean} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Boolean} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setBoolean(String key, boolean value) {
		return set(key, value);
	}
	
	/**
	 * Save an {@link Configuration} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Configuration} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setConfiguration(String key, Configuration value) {
		return set(key, value.toJsonObject());
	}
	
	/**
	 * Save an {@link JSONArray} value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link JSONArray} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public Object setJSONArray(String key, JSONArray value) {
		return set(key, value);
	}
	
	/**
	 * Save an Enum Value in the {@link Configuration}. If the value is null, then the key will be removed from the {@link Configuration} if it is present.
	 *
	 * @param key   A key string.
	 * @param value The {@link Enum} Value to save.
	 * @return The value previously assigned to that key. null if the key wasn't set.
	 * @throws JSONException        If the value is non-finite number.
	 * @throws NullPointerException If the key is null.
	 */
	public <E extends Enum<E>> Object setEnum(String key, E value) throws JSONException, NullPointerException {
		return set(key, value);
	}
	
	/**
	 * Get a set of keys of the {@link Configuration}. Modifying it will have no impact on the {@link Configuration} itself.
	 *
	 * @return A keySet.
	 * @see Map#keySet()
	 */
	public Set<String> getKeys() {
		return Set.copyOf(content.keySet());
	}
	
	/**
	 * Determine if the {@link Configuration} contains a specific key.
	 *
	 * @param key A key string.
	 * @return true if the key exists in the Configuration.
	 */
	public boolean hasKey(String key) {
		return content.has(key);
	}
	
	/**
	 * Make a JSON text of this {@link Configuration}. For compactness, no whitespace is added.
	 *
	 * @return a printable, displayable, portable, transmittable representation
	 * *         of the object, beginning with <code>{</code>&nbsp;<small>(left
	 * *         brace)</small> and ending with <code>}</code>&nbsp;<small>(right
	 * *         brace)</small>.
	 */
	@Override
	public String toString() {
		return content.toString();
	}
	
	protected JSONObject toJsonObject() {
		return content;
	}
	
}