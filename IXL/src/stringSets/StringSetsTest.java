package stringSets;

import static org.junit.Assert.*;

import org.junit.Test;

import static stringSets.StringSets.*;

public class StringSetsTest {

	@Test
	public void testAllStringSetsIdentical() {
		assertTrue(allStringSetsIdentical(new String[][] 
				{{"a","b"},{"b","b","a"},{"b","a"}}));
		assertFalse(allStringSetsIdentical(new String[][] 
				{{"a","b"},{"a"},{"b"}}));
	}

}
