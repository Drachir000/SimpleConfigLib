package de.drachir000.utils.config;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class SimpleConfigLibTest {
	
	@Test
	public void testEmptyConfiguration() {
		
		Configuration config = SimpleConfigLib.emptyConfiguration();
		
		assertNotNull(config);
		
		assertThrows(JSONException.class, () -> config.get("invalid-key"));
		
	}
	
	@Test
	public void testConfigurationFromJSONObject() {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key-prev", "value-prev");
		
		Configuration config = SimpleConfigLib.buildConfiguration(jsonObject);
		assertNotNull(config);
		assertEquals("value-prev", config.getString("key-prev"));
		
		config.setString("key-after", "value-after");
		assertEquals("value-after", jsonObject.getString("key-after"));
		
		assertThrows(JSONException.class, () -> config.get("invalid-key"));
		
	}
	
	@Test
	public void testConfigurationFromString() {
		
		String source = "{\"key\":\"value\"}";
		
		Configuration config = SimpleConfigLib.buildConfiguration(source);
		assertNotNull(config);
		assertEquals("value", config.getString("key"));
		
		assertThrows(JSONException.class, () -> config.get("invalid-key"));
		
	}
	
	@Test
	public void testSaveFile1() throws IOException {
		
		Configuration configuration = SimpleConfigLib.emptyConfiguration();
		File file = new File("settings.json");
		
		if (file.exists())
			assertTrue(file.delete());
		
		configuration.setString("key", "value");
		
		SimpleConfigLib.save(configuration, file);
		
		assertTrue(file.exists());
		
		Configuration loadedConfig = SimpleConfigLib.load(file);
		assertEquals("value", loadedConfig.getString("key"));
		
	}
	
	@Test
	public void testSaveFile2() throws IOException, ClassNotFoundException {
		
		Configuration configuration = SimpleConfigLib.emptyConfiguration();
		File file = new File("settings.json");
		ConfigurationTest.TestObject value = new ConfigurationTest.TestObject(123, "Hello World!", ConfigurationTest.TestEnum.VALUE_THREE, 456.78f);
		
		if (file.exists())
			assertTrue(file.delete());
		
		configuration.set("key", value);
		
		assertEquals(value, configuration.get("key"));
		assertFalse(configuration.isEncodedObject("key"));
		
		SimpleConfigLib.save(configuration, file, true);
		
		assertTrue(file.exists());
		
		Configuration loadedConfig1 = SimpleConfigLib.load(file);
		assertTrue(loadedConfig1.isEncodedObject("key"));
		assertEquals(value, loadedConfig1.getEncoded("key"));
		
		if (file.exists())
			assertTrue(file.delete());
		
		SimpleConfigLib.save(configuration, file);
		
		assertTrue(file.exists());
		
		Configuration loadedConfig2 = SimpleConfigLib.load(file);
		assertFalse(loadedConfig2.isEncodedObject("key"));
		assertThrows(IllegalArgumentException.class, () -> loadedConfig2.getEncoded("key"));
		
	}
	
	@Test
	public void testLoadFile() throws IOException {
		
		File file = new File("settings.json");
		
		if (file.exists())
			assertTrue(file.delete());
		
		Configuration configuration = SimpleConfigLib.load(file);
		
		configuration.setString("key", "value");
		
		SimpleConfigLib.save(configuration, file);
		
		assertTrue(file.exists());
		
		Configuration loadedConfig = SimpleConfigLib.load(file);
		assertEquals("value", loadedConfig.getString("key"));
		
	}
	
}