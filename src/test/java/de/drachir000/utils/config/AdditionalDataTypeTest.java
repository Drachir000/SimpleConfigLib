package de.drachir000.utils.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AdditionalDataTypeTest {
	
	@Test
	public void testUnsupported() {
		
		Configuration configuration = SimpleConfigLib.emptyConfiguration();
		
		configuration.set("long", 99999999L);
		configuration.set("double", 1.23F);
		
		configuration.set("byte", (byte) 99);
		configuration.set("short", (short) 999);
		configuration.set("char", 'r');
		
		assertEquals(99999999L, configuration.get("long"));
		assertEquals(1.23F, configuration.get("double"));
		
		assertEquals((byte) 99, configuration.get("byte"));
		assertEquals((short) 999, configuration.get("short"));
		assertEquals('r', configuration.get("char"));
		
		Configuration newConfig = SimpleConfigLib.buildConfiguration(configuration.toString());
		
		assertNotEquals(1.23D, newConfig.get("double"));
		assertEquals(1.23D, newConfig.getDouble("double"), 0.1);
		
		assertNotEquals(99999999L, newConfig.get("long"));
		assertEquals(99999999L, newConfig.getLong("long"));
		
		assertNotEquals((byte) 99, newConfig.get("byte"));
		assertNotEquals((short) 999, newConfig.get("short"));
		assertNotEquals('r', newConfig.get("char"));
		
		assertEquals((byte) 99, newConfig.getByte("byte"));
		assertEquals((short) 999, newConfig.getShort("short"));
		assertEquals('r', newConfig.getChar("char"));
		
	}
	
}
