package states;

import static org.junit.Assert.*;

import org.junit.Test;

import static states.StateUtils.*;

public class StateUtilsTest {
	
	@Test
	public void testdisplayStateFullName() {
		assertEquals(displayStateFullName("AK"), "Alaska");
		assertNull(displayStateFullName("AB"));
	}
	
	@Test
	public void testParseSelectedState() {
		assertEquals(parseSelectedState("California"), "CA");
		assertNull(parseSelectedState("testState"));
	}

	@Test
	public void testCreateStateSelectList() {
		assertEquals(createStateSelectList(), 
			"<select name=\"state\">\n"
			+ "<option value=\"Alabama\">Alabama</option>\n"
			+ "<option value=\"Alaska\">Alaska</option>\n"
			+ "<option value=\"Arizona\">Arizona</option>\n"
			+ "<option value=\"Arkansas\">Arkansas</option>\n"
			+ "<option value=\"California\">California</option>\n"
			+ "</select>\n");
	}
	
}
