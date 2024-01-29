package de.drachir000.utils.config;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * SimpleConfigLib is a class that provides static utility methods for working with configurations.
 */
public class SimpleConfigLib {
	
	/**
	 * Creates an empty configuration.
	 *
	 * @return an empty {@link Configuration} object
	 * @since 1.1
	 */
	public static Configuration emptyConfiguration() {
		return buildConfiguration(new JSONObject());
	}
	
	/**
	 * Constructs a {@link Configuration} object from a {@link JSONObject}.
	 * Note that modifying the {@link JSONObject} <b>will</b> impact the {@link Configuration} and vice versa.
	 *
	 * @param jsonObject the {@link JSONObject} representing the {@link Configuration}
	 * @return a {@link Configuration} object created from the {@link JSONObject}
	 * @since 1.1
	 */
	public static Configuration buildConfiguration(JSONObject jsonObject) {
		return new Configuration(jsonObject);
	}
	
	/**
	 * Constructs a {@link Configuration} object from a JSON string.
	 *
	 * @param source the JSON string representing the configuration
	 * @return a {@link Configuration} object created from the JSON string
	 * @throws JSONException If there is a syntax error in the source string or a duplicated key.
	 * @since 1.1
	 */
	public static Configuration buildConfiguration(String source) throws JSONException {
		return buildConfiguration(new JSONObject(source));
	}
	
	/**
	 * Saves a {@link Configuration} to a {@link File}.
	 *
	 * @param configuration the {@link Configuration} to be saved
	 * @param file          the {@link File} to save the {@link Configuration} to
	 * @throws IOException if an I/O error occurs while writing to the file
	 * @since 1.3
	 */
	public static void save(Configuration configuration, File file) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(configuration.toString());
		
		writer.flush();
		writer.close();
		
	}
	
	/**
	 * Saves a {@link Configuration} to a {@link File}.
	 *
	 * @param configuration        the {@link Configuration} to be saved
	 * @param file                 the {@link File} to save the {@link Configuration} to
	 * @param encodeUnknownObjects represents whether to encode unknown objects. If this is set to true, any {@link Serializable}
	 *                             object, which is not recognized and hence would be converted into a String for the result, will be
	 *                             serialized and Base64 encoded. Such objects can then be fetched using {@link Configuration#getEncoded(String)}.
	 *                             This setting does not modify this Object, only the manner these objects are stored within the File by this method.
	 * @throws IOException if an I/O error occurs while writing to the file
	 * @since 1.3
	 */
	public static void save(Configuration configuration, File file, boolean encodeUnknownObjects) throws IOException {
		
		if (!encodeUnknownObjects) {
			save(configuration, file);
			return;
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(configuration.toString(true));
		
		writer.flush();
		writer.close();
		
	}
	
	/**
	 * Reads a JSON string from a {@link File} and constructs a {@link Configuration} object from it.
	 *
	 * @param file the {@link File} to read the JSON string from
	 * @return a {@link Configuration} object constructed from the JSON string
	 * @throws IOException   if an I/O error occurs while reading the file
	 * @throws JSONException if there is a syntax error in the JSON string or a duplicated key
	 * @since 1.3
	 */
	public static Configuration load(File file) throws IOException, JSONException {
		
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			
			reader.close();
		} catch (FileNotFoundException ignored) {
		}
		
		if (builder.toString().isBlank())
			return emptyConfiguration();
		
		return buildConfiguration(builder.toString());
		
	}
	
}
