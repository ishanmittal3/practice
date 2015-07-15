package arrays_strings;

import static org.junit.Assert.*;

import org.junit.Test;

import static arrays_strings.ReplaceSpaces.*;

public class ReplaceSpacesTest {

	@Test
	public void testReplaceSpaces() {
		assertEquals(replaceSpaces(""), "");
		assertEquals(replaceSpaces(" "), "%20");
		assertEquals(replaceSpaces(" string"), "%20string");
		assertEquals(replaceSpaces("string "), "string%20");
		assertEquals(replaceSpaces(" string "), "%20string%20");
		assertEquals(replaceSpaces("string String"), "string%20String");
	}

}
