package de.drachir000.utils.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Base64;
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
	 * @deprecated This is planned to be replaced by {@link SimpleConfigLib#buildConfiguration(JSONObject)}!
	 * @see SimpleConfigLib
	 */
	@Deprecated
	public Configuration(JSONObject jsonObject) {
		this.content = jsonObject;
	}
	
	/**
	 * Get the value {@link Object} associated with a key.
	 * <b>Caution: Objects without explicit getters might get assigned by {@link Configuration#set(String, Object)}. However,
	 * while serializing the {@link Configuration} ({@link Configuration#toString()}),
	 * such {@link Configuration}s that are constructed from this string ({@link SimpleConfigLib#buildConfiguration(String)})
	 * will contain a string representation of the Object associated with the key!
	 * To prevent this, employ {@link Configuration#setEncoded(String, Serializable)} for setting
	 * and {@link Configuration#getEncoded(String)} for retrieving these objects.</b>
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
	 * Retrieves the {@link Serializable} {@link Object} value associated Base64 encoded with the given key.
	 *
	 * @param key The key of the value to retrieve.
	 * @return The decoded {@link Serializable} {@link Object} associated with the given key.
	 * @throws JSONException            If a JSON error occurs during the retrieval.
	 * @throws IOException              If an IO error occurs during the retrieval.
	 * @throws ClassNotFoundException   If a class cannot be found during the deserialization.
	 * @throws IllegalArgumentException If an invalid argument is provided.
	 * @throws SecurityException        If a security violation occurs during the retrieval.
	 * @throws NullPointerException     If the value associated with the given key is null or blank.
	 * @see Configuration#getEncodedOrDefault(String, Object)
	 */
	public Object getEncoded(String key) throws JSONException, IOException, ClassNotFoundException, IllegalArgumentException, SecurityException, NullPointerException {
		String encoded = getString(key);
		return deserialize(encoded);
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
	 * <b>Caution: Objects without explicit getters might get assigned by {@link Configuration#set(String, Object)}. However,
	 * while serializing the {@link Configuration} ({@link Configuration#toString()}),
	 * such {@link Configuration}s that are constructed from this string ({@link SimpleConfigLib#buildConfiguration(String)})
	 * will contain a string representation of the Object associated with the key!
	 * To prevent this, employ {@link Configuration#setEncoded(String, Serializable)} for setting
	 * and {@link Configuration#getEncodedOrDefault(String, Object)} for retrieving these objects.</b>
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
	 * Get the decoded {@link Serializable} {@link Object} associated with a key, or the provided replacement value if the key is not set.
	 *
	 * @param key          A key string.
	 * @param defaultValue The fallback value
	 * @return The {@link Serializable} {@link Object} associated encoded with the key or the fallback value
	 * @see Configuration#getEncoded(String)
	 */
	public Object getEncodedOrDefault(String key, Object defaultValue) {
		if (!hasKey(key))
			return defaultValue;
		try {
			return getEncoded(key);
		} catch (JSONException | IOException | ClassNotFoundException | IllegalArgumentException | SecurityException | NullPointerException ignored) {
			return defaultValue;
		}
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
	 * <b>Caution: Objects without explicit setters might get assigned by this method. However,
	 * while serializing the {@link Configuration} ({@link Configuration#toString()}),
	 * such {@link Configuration}s that are constructed from this string ({@link SimpleConfigLib#buildConfiguration(String)})
	 * will contain a string representation of the Object associated with the key!
	 * To prevent this, employ {@link Configuration#setEncoded(String, Serializable)} for setting
	 * and {@link Configuration#getEncoded(String)} for retrieving these objects.</b>
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
	 * Saves an {@link Serializable} {@link Object} value in the {@link Configuration}.
	 * The value will be serialized and then stored as a Base64 encoded String.
	 *
	 * @param key   the key to set
	 * @param value the value to set
	 * @return the previous value associated with the key, or {@code null} if there was no previous value
	 * @throws IOException          if there was an IO error during serialization
	 * @throws SecurityException    if a security violation occurs
	 * @throws NullPointerException if the key or value is {@code null}
	 */
	public Object setEncoded(String key, Serializable value) throws IOException, SecurityException, NullPointerException {
		Object prevValue = getEncodedOrDefault(key, null);
		content.put(key, serialize(value));
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
	 * Determines whether the specified key corresponds to a Base64 encoded {@link Serializable} {@link Object}.
	 *
	 * @param key the key to check
	 * @return true if the key corresponds to an encoded object, false otherwise
	 */
	public boolean isEncodedObject(String key) {
		if (!hasKey(key))
			return false;
		return getEncodedOrDefault(key, null) != null;
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
	
	/**
	 * Deserializes a serialized object.
	 *
	 * @param s the serialized object as a Base64 encoded string
	 * @return the deserialized object
	 * @throws IOException              if an I/O error occurs while deserializing
	 * @throws ClassNotFoundException   if the class of the object to be deserialized is not found
	 * @throws IllegalArgumentException if the string is null or empty
	 * @throws SecurityException        if a security manager exists and its checkPermission method denies permission to deserialize
	 * @throws NullPointerException     if the string is null or empty
	 */
	protected static Object deserialize(String s) throws IOException, ClassNotFoundException, IllegalArgumentException, SecurityException, NullPointerException {
		
		if (s == null || s.isBlank())
			throw new NullPointerException("Cannot deserialize null or empty String!");
		
		byte[] data = Base64.getDecoder().decode(s);
		
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		
		Object o = ois.readObject();
		ois.close();
		
		return o;
		
	}
	
	/**
	 * Serializes an object into a Base64 encoded string.
	 *
	 * @param o The object to be serialized. Must implement the Serializable interface.
	 * @return A Base64 encoded string representation of the serialized object.
	 * @throws IOException          If an I/O error occurs while serializing the object.
	 * @throws SecurityException    If a security violation occurs during serialization.
	 * @throws NullPointerException If the object passed is null.
	 */
	protected static String serialize(Serializable o) throws IOException, SecurityException, NullPointerException {
		
		if (o == null)
			throw new NullPointerException("null cannot be serialized!");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		
		oos.writeObject(o);
		oos.close();
		
		return Base64.getEncoder().encodeToString(baos.toByteArray());
		
	}
	
}
