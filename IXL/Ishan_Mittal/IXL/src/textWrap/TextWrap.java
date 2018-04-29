package textWrap;

public class TextWrap {
	
	public static final String eol = System.getProperty("line.separator");
	
	public static String wrapText(String text, int maxCharsPerLine) {
		String result = "";
		String[] lines = text.split(eol);
		String currLine = "";
		
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {

			lines[lineIndex] = lines[lineIndex].trim();

			if (lines[lineIndex].length() > maxCharsPerLine) {
				String[] words = lines[lineIndex].split(" ");

				for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {

					// Insert a space
					// unless it's the beginning of the line
					// or it's the last word in the line
					if (!currLine.isEmpty() &&	words[wordIndex].length() > 0) {  
						currLine = currLine + " ";
					}

					// If the current line has space to fill
					// and the next word is longer than maxCharsPerLine
					if (currLine.length() < maxCharsPerLine &&
							words[wordIndex].length() > maxCharsPerLine) {
						int cutoff = maxCharsPerLine - currLine.length();
						currLine = currLine + words[wordIndex].substring(0, cutoff);
						words[wordIndex] = words[wordIndex].substring(cutoff);

						result = result + currLine;
						result = result + eol;
						currLine = "";
					}

					// If the next word will fit in the current line
					if (currLine.length() + words[wordIndex].length() <= maxCharsPerLine) {
						currLine = currLine + words[wordIndex];
					}
					else {
						if (!currLine.isEmpty()) {
							result = result + currLine.trim();
							currLine = "";

							// Insert eol unless it's the last word
							if (wordIndex < words.length -1) {
								result = result + eol;
							}
						}
						// Since the current word couldn't fit in the current line
						// it has to be reconsidered (for the next line)
						wordIndex--;
					}
				}
				
				result = result + currLine.trim();
				currLine = "";
			}
			else {
				if (currLine.isEmpty()) {
					result = result + lines[lineIndex];
				}
				else {
					// currLine has a leftover part from the previous line
					// that couldn't fit
					result = result + currLine + eol + lines[lineIndex];
				}
			}

			// Insert eol unless it's the last line
			if (lineIndex < lines.length -1) {
				result = result + eol;
			}
		}
		
		return result;
	}
	
}
