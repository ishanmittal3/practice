package textWrap;

import static org.junit.Assert.*;

import org.junit.Test;
import static textWrap.TextWrap.*;

public class TextWrapTest {

	@Test
	public void testWrapText() {
		assertEquals(wrapText("A " 									  + eol + 
																		eol +
		 		  			  " an  ant thesaurus best 12345678 sol " + eol +
		 		  			  "a"
		 		  			  , 6),
		 		  			  
		 		  			  "A"	   + eol 
		 		  			  		   + eol + 
					 		  "an ant" + eol + 
					 		  "thesau" + eol +
					 		  "rus"    + eol +
					 		  "best 1" + eol +
					 		  "234567" + eol +
					 		  "8 sol"  + eol +
					 		  "a"
					 		  );
	}

}
