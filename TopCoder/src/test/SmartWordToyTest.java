package test;

import static main.SmartWordToy.minPresses;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SmartWordToyTest {

	@Test
	public void testMinPresses() {
		assertEquals(8, minPresses("aaaa", "zzzz", new String[]
				{"a a a z", "a a z a", "a z a a", "z a a a", 
				 "a z z z", "z a z z", "z z a z", "z z z a"}));
		assertEquals(4, minPresses("aaaa", "bbbb", new String[] {}));
		assertEquals(50, minPresses("aaaa", "mmnn", new String[] {}));
		assertEquals(-1, minPresses("aaaa", "zzzz", new String[] 
				{"bz a a a", "a bz a a", "a a bz a", "a a a bz"}));
		assertEquals(6, minPresses("aaaa", "zzzz", new String[] 
				{"cdefghijklmnopqrstuvwxyz a a a", 
				 "a cdefghijklmnopqrstuvwxyz a a", 
				 "a a cdefghijklmnopqrstuvwxyz a", 
				 "a a a cdefghijklmnopqrstuvwxyz"}));
		assertEquals(-1, minPresses("aaaa", "bbbb", new String[] {"b b b b"}));
	}

}
