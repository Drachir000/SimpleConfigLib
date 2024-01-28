package de.drachir000.utils.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ConfigurationTest {
	
	@Test
	public void testGet() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45f);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123.45f, configuration.get("key"));
		
	}
	
	@Test
	public void testGetString() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", "value");
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("value", configuration.getString("key"));
		
	}
	
	@Test
	public void testGetInt() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123, configuration.getInt("key"));
		
	}
	
	@Test
	public void testGetLong() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123L);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123L, configuration.getLong("key"));
		
	}
	
	@Test
	public void testGetDouble() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123.45, configuration.getDouble("key"), 0.01);
		
	}
	
	@Test
	public void testGetFloat() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45f);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123.45f, configuration.getFloat("key"), 0.01);
		
	}
	
	@Test
	public void testGetBoolean() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", true);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertTrue(configuration.getBoolean("key"));
		
	}
	
	@Test
	public void testGetConfiguration1() {
		
		JSONObject jsonObjectInner = new JSONObject();
		jsonObjectInner.put("keyInner", "valueInner");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", jsonObjectInner);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("valueInner", configuration.getConfiguration("key").getString("keyInner"));
		
	}
	
	@Test
	public void testGetConfiguration2() {
		
		Configuration jsonObjectInner = new Configuration(new JSONObject());
		jsonObjectInner.setString("keyInner", "valueInner");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", jsonObjectInner);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("valueInner", configuration.getConfiguration("key").getString("keyInner"));
		
	}
	
	@Test
	public void testGetJSONArray() {
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		jsonArray.put("value1");
		jsonArray.put("value2");
		
		jsonObject.put("key", jsonArray);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("value1", configuration.getJSONArray("key").getString(0));
		assertEquals("value2", configuration.getJSONArray("key").getString(1));
		
	}
	
	@Test
	public void testGetEnum() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", TestEnum.VALUE_TWO);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(TestEnum.VALUE_TWO, configuration.getEnum(TestEnum.class, "key"));
		
	}
	
	@Test
	public void testGetDefault() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45f);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123.45f, configuration.getOrDefault("key", (Object) "default-value"));
		assertEquals(123.45f, configuration.getOrDefault("key", (Object) 567.89f));
		
	}
	
	@Test
	public void testGetStringDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", "value");
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("default-value", configuration.getOrDefault("invalid-key", "default-value"));
		
	}
	
	@Test
	public void testGetIntDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(456, configuration.getOrDefault("invalid-key", 456));
		
	}
	
	@Test
	public void testGetLongDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123L);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(456L, configuration.getOrDefault("invalid-key", 456L));
		
	}
	
	@Test
	public void testGetDoubleDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(678.91, configuration.getOrDefault("invalid-key", 678.91), 0.01);
		
	}
	
	@Test
	public void testGetFloatDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45f);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(678.91f, configuration.getOrDefault("invalid-key", 678.91f), 0.01);
		
	}
	
	@Test
	public void testGetBooleanDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", true);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertFalse(configuration.getOrDefault("invalid-key", false));
		
	}
	
	@Test
	public void testGetConfiguration1Default1() {
		
		JSONObject jsonObjectInner = new JSONObject();
		jsonObjectInner.put("keyInner", "valueInner");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", jsonObjectInner);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("valueInner", configuration.getOrDefault("invalid-key", new Configuration(jsonObjectInner)).getString("keyInner"));
		assertEquals("default-value", configuration.getConfiguration("key").getOrDefault("invalid-key", "default-value"));
		
	}
	
	@Test
	public void testGetConfiguration2Default1() {
		
		Configuration jsonObjectInner = new Configuration(new JSONObject());
		jsonObjectInner.setString("keyInner", "valueInner");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", jsonObjectInner);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("valueInner", configuration.getOrDefault("invalid-key", jsonObjectInner).getString("keyInner"));
		assertEquals("default-value", configuration.getConfiguration("key").getOrDefault("invalid-key", "default-value"));
		
	}
	
	@Test
	public void testGetJSONArrayDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		jsonArray.put("value1");
		jsonArray.put("value2");
		
		jsonObject.put("key", jsonArray);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("value1", configuration.getOrDefault("invalid-key", jsonArray).getString(0));
		assertEquals("value2", configuration.getOrDefault("invalid-key", jsonArray).getString(1));
		
	}
	
	@Test
	public void testGetEnumDefault1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", TestEnum.VALUE_TWO);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(TestEnum.VALUE_ONE, configuration.getOrDefault("invalid-key", TestEnum.VALUE_ONE));
		assertEquals(TestEnum.VALUE_ONE, configuration.getOrDefault(TestEnum.class, "invalid-key", TestEnum.VALUE_ONE));
		
	}
	
	@Test
	public void testGetStringDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", "value");
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("value", configuration.getOrDefault("key", "default-value"));
		
	}
	
	@Test
	public void testGetIntDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123, configuration.getOrDefault("key", 456));
		
	}
	
	@Test
	public void testGetLongDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123L);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123L, configuration.getOrDefault("key", 456L));
		
	}
	
	@Test
	public void testGetDoubleDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123.45, configuration.getOrDefault("key", 678.91), 0.01);
		
	}
	
	@Test
	public void testGetFloatDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", 123.45f);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(123.45f, configuration.getOrDefault("key", 678.91f), 0.01);
		
	}
	
	@Test
	public void testGetBooleanDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", true);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertTrue(configuration.getOrDefault("key", false));
		
	}
	
	@Test
	public void testGetConfiguration1Default2() {
		
		JSONObject jsonObjectInner = new JSONObject();
		jsonObjectInner.put("keyInner", "valueInner");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", jsonObjectInner);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("valueInner", configuration.getOrDefault("key", new Configuration(new JSONObject())).getString("keyInner"));
		assertEquals("valueInner", configuration.getConfiguration("key").getOrDefault("keyInner", "default-value"));
		
	}
	
	@Test
	public void testGetConfiguration2Default2() {
		
		Configuration jsonObjectInner = new Configuration(new JSONObject());
		jsonObjectInner.setString("keyInner", "valueInner");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", jsonObjectInner);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("valueInner", configuration.getOrDefault("key", new Configuration(new JSONObject())).getString("keyInner"));
		assertEquals("valueInner", configuration.getConfiguration("key").getOrDefault("keyInner", "default-value"));
		
	}
	
	@Test
	public void testGetJSONArrayDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		jsonArray.put("value1");
		jsonArray.put("value2");
		
		jsonObject.put("key", jsonArray);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("value1", configuration.getOrDefault("key", new JSONArray()).getString(0));
		assertEquals("value2", configuration.getOrDefault("key", new JSONArray()).getString(1));
		
	}
	
	@Test
	public void testGetEnumDefault2() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", TestEnum.VALUE_TWO);
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals(TestEnum.VALUE_TWO, configuration.getOrDefault("key", TestEnum.VALUE_ONE));
		assertEquals(TestEnum.VALUE_TWO, configuration.getOrDefault(TestEnum.class, "key", TestEnum.VALUE_ONE));
		
	}
	
	@Test
	public void testSet() {
		
		Configuration configuration = new Configuration(new JSONObject());
		
		configuration.set("key", 123.45f);
		
		assertEquals(123.45f, configuration.get("key"));
		
	}
	
	@Test
	public void testSetString() {
		
		Configuration configuration = new Configuration(new JSONObject());
		
		configuration.setString("key", "value");
		
		assertEquals("value", configuration.getString("key"));
		
	}
	
	@Test
	public void testSetInt() {
		
		Configuration configuration = new Configuration(new JSONObject());
		
		configuration.setInt("key", 123);
		
		assertEquals(123, configuration.getInt("key"));
		
	}
	
	@Test
	public void testSetLong() {
		
		Configuration configuration = new Configuration(new JSONObject());
		
		configuration.setLong("key", 123L);
		
		assertEquals(123L, configuration.getLong("key"));
		
	}
	
	@Test
	public void testSetDouble() {
		
		Configuration configuration = new Configuration(new JSONObject());
		
		configuration.setDouble("key", 123.45);
		
		assertEquals(123.45, configuration.getDouble("key"), 0.01);
		
	}
	
	@Test
	public void testSetFloat() {
		
		Configuration configuration = new Configuration(new JSONObject());
		
		configuration.setFloat("key", 123.45f);
		
		assertEquals(123.45f, configuration.getFloat("key"), 0.01);
		
	}
	
	@Test
	public void testSetBoolean() {
		
		Configuration configuration = new Configuration(new JSONObject());
		
		configuration.setBoolean("key", true);
		
		assertTrue(configuration.getBoolean("key"));
		
	}
	
	@Test
	public void testSetConfiguration1() {
		
		JSONObject jsonObjectInner = new JSONObject();
		jsonObjectInner.put("keyInner", "valueInner");
		
		Configuration configuration = new Configuration(new JSONObject());
		configuration.setConfiguration("key", new Configuration(jsonObjectInner));
		
		assertEquals("valueInner", configuration.getConfiguration("key").getString("keyInner"));
		
	}
	
	@Test
	public void testSetConfiguration2() {
		
		Configuration configurationInner = new Configuration(new JSONObject());
		configurationInner.setString("keyInner", "valueInner");
		
		Configuration configuration = new Configuration(new JSONObject());
		configuration.setConfiguration("key", configurationInner);
		
		assertEquals("valueInner", configuration.getConfiguration("key").getString("keyInner"));
		
	}
	
	@Test
	public void testSetJSONArray() {
		
		Configuration configuration = new Configuration(new JSONObject());
		JSONArray jsonArray = new JSONArray();
		
		jsonArray.put("value1");
		jsonArray.put("value2");
		
		configuration.setJSONArray("key", jsonArray);
		
		assertEquals("value1", configuration.getJSONArray("key").getString(0));
		assertEquals("value2", configuration.getJSONArray("key").getString(1));
		
	}
	
	@Test
	public void testSetEnum() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("two", TestEnum.VALUE_TWO);
		
		Configuration configuration = new Configuration(jsonObject);
		
		configuration.setEnum("three", TestEnum.VALUE_THREE);
		
		assertEquals(TestEnum.VALUE_TWO, configuration.getEnum(TestEnum.class, "two"));
		assertEquals(TestEnum.VALUE_THREE, configuration.getEnum(TestEnum.class, "three"));
		
	}
	
	@Test
	public void testHasKey() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", "value");
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertTrue(configuration.hasKey("key"));
		assertFalse(configuration.hasKey("nonExistingKey"));
		
	}
	
	@Test
	public void testGetKeys() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key1", "value1");
		jsonObject.put("key2", "value2");
		
		Configuration configuration = new Configuration(jsonObject);
		
		Set<String> keys = configuration.getKeys();
		assertEquals(2, keys.size());
		
		assertTrue(keys.contains("key1"));
		assertTrue(keys.contains("key2"));
		
	}
	
	@Test
	public void testToString() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", "value");
		
		Configuration configuration = new Configuration(jsonObject);
		
		assertEquals("{\"key\":\"value\"}", configuration.toString());
		
	}
	
	@Test
	public void testDeserialization1() {
		
		JSONObject jsonObject = generateConfigDeserialization1();
		
		Configuration configuration = new Configuration(
				new JSONObject(
						new Configuration(jsonObject).toString()
				)
		);
		
		assertEquals("value", configuration.getString("string"));
		assertEquals(123, configuration.getInt("int"));
		assertEquals(456L, configuration.getLong("long"));
		assertEquals(123.45, configuration.getDouble("double"), 0.01);
		assertEquals(567.89f, configuration.getFloat("float"), 0.01);
		assertTrue(configuration.getBoolean("boolean"));
		assertEquals("valueInner1", configuration.getConfiguration("config-1").getString("keyInner"));
		assertEquals("valueInner2", configuration.getConfiguration("config-2").getString("keyInner"));
		assertEquals("value1", configuration.getJSONArray("array").getString(0));
		assertEquals("value2", configuration.getJSONArray("array").getString(1));
		assertEquals(TestEnum.VALUE_ONE, configuration.getEnum(TestEnum.class, "enum"));
		
	}
	
	private static JSONObject generateConfigDeserialization1() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("string", "value");
		
		jsonObject.put("int", 123);
		jsonObject.put("long", 456L);
		jsonObject.put("double", 123.45);
		jsonObject.put("float", 567.89f);
		jsonObject.put("boolean", true);
		
		JSONObject jsonObjectInner = new JSONObject();
		jsonObjectInner.put("keyInner", "valueInner1");
		jsonObject.put("config-1", jsonObjectInner);
		
		Configuration configurationInner = new Configuration(new JSONObject());
		configurationInner.setString("keyInner", "valueInner2");
		jsonObject.put("config-2", configurationInner);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("value1");
		jsonArray.put("value2");
		jsonObject.put("array", jsonArray);
		
		jsonObject.put("enum", TestEnum.VALUE_ONE);
		
		return jsonObject;
		
	}
	
	@Test
	public void testDeserialization2() {
		
		Configuration configObject = getConfigurationDeserialization2();
		
		Configuration configuration = new Configuration(
				new JSONObject(
						configObject.toString()
				)
		);
		
		assertEquals("value", configuration.getString("string"));
		assertEquals(123, configuration.getInt("int"));
		assertEquals(456L, configuration.getLong("long"));
		assertEquals(123.45, configuration.getDouble("double"), 0.01);
		assertEquals(567.89f, configuration.getFloat("float"), 0.01);
		assertTrue(configuration.getBoolean("boolean"));
		assertEquals("valueInner1", configuration.getConfiguration("config-1").getString("keyInner"));
		assertEquals("valueInner2", configuration.getConfiguration("config-2").getString("keyInner"));
		assertEquals("value1", configuration.getJSONArray("array").getString(0));
		assertEquals("value2", configuration.getJSONArray("array").getString(1));
		assertEquals(TestEnum.VALUE_TWO, configuration.getEnum(TestEnum.class, "enum"));
		
	}
	
	private static Configuration getConfigurationDeserialization2() {
		
		Configuration configObject = new Configuration(new JSONObject());
		
		configObject.setString("string", "value");
		
		configObject.setInt("int", 123);
		configObject.setLong("long", 456L);
		configObject.setDouble("double", 123.45);
		configObject.setFloat("float", 567.89f);
		configObject.setBoolean("boolean", true);
		
		JSONObject jsonObjectInner = new JSONObject();
		jsonObjectInner.put("keyInner", "valueInner1");
		configObject.setConfiguration("config-1", new Configuration(jsonObjectInner));
		
		Configuration configurationInner = new Configuration(new JSONObject());
		configurationInner.setString("keyInner", "valueInner2");
		configObject.setConfiguration("config-2", configurationInner);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put("value1");
		jsonArray.put("value2");
		configObject.setJSONArray("array", jsonArray);
		
		configObject.setEnum("enum", TestEnum.VALUE_TWO);
		
		return configObject;
		
	}
	
	@Test
	public void testOOP() {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("key", "value");
		
		Configuration configuration1 = new Configuration(jsonObject);
		Configuration configuration2 = new Configuration(jsonObject);
		
		configuration1.setString("key-2", "value-2");
		
		assertEquals("value", configuration1.getString("key"));
		assertEquals("value", configuration2.getString("key"));
		assertEquals("value-2", configuration2.getString("key-2"));
		assertEquals("value-2", jsonObject.getString("key-2"));
		
	}
	
	private enum TestEnum {
		
		VALUE_ONE,
		VALUE_TWO,
		VALUE_THREE;
		
	}
	
}
