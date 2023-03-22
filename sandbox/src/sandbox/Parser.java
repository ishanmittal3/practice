package sandbox;

// Zip interview
public class Parser {

/*
 Zip’s dashboard allows users to leave comments. A user receives an email notification for new comments.

We’re building a new feature where users can reply to the notification emails, and their reply is stored as a comment back on the Zip dashboard.


Link for screenshots: https://i.imgur.com/tupNedE.png

To do this, we need to build a parser to convert text from email messages into HTML/React code.

We’ll start by supporting a few basic text formatting features: paragraphs, quotes, and line breaks. The React components are <Paragraph>, <Quote>, and <Break />.

Sample input:
message = "Please tag @Bob Jones for approval!\n\n" // Paragraph
+ "Here's the max amount we were allotted:\n\n" // Paragraph
+ ">Max spend: $5000\n" // Start quote, line break
+ ">Max spend per user: $12\n" // Quote, line break
+ ">Target spend: $3000\n\n" // End quote
+ "Do you think it will be ok?\nOtherwise let's consider a new vendor." // Paragraph with line break


Sample output:
<Paragraph>Please tag @Bob Jones for approval!</Paragraph>
<Paragraph>Here's the max amount we were allotted:</Paragraph>
<Quote>Max spend: $5000<Break />Max spend per user: $12<Break />Target spend: $3000</Quote>
<Paragraph>Do you think it will be ok?<Break />Otherwise let's consider a new vendor.</Paragraph>


Raw input:
message = "Please tag @Bob Jones for approval!\n\nHere's the max amount we were allotted:\n\n>Max spend: $5000\n>Max spend per user: $12\n>Target spend: $3000\n\nDo you think it will be ok?\nOtherwise let's consider a new vendor."

-----------------
We also want to support nested quotes. Nested quotes are denoted by continuous “>” characters.

Sample input:
message = "Please tag @Bob Jones for approval!\n\n" # Paragraph
+ "Here's the max amount we were allotted:\n\n" # Paragraph
+ ">Max spend: $5000\n" # Start quote, line break
+ ">>Max spend per user: $12\n" # Quote, line break
+ ">>Target spend: $3000\n\n" # End quote
+ "Do you think it will be ok?\nOtherwise let's consider a new vendor." # Paragraph with line break


Sample output:
<Paragraph>Please tag @Bob Jones for approval!</Paragraph>
<Paragraph>Here's the max amount we were allotted:</Paragraph>
<Quote>Max spend: $5000<Break /><Quote>Max spend per user: $12<Break />Target spend: $3000</Quote></Quote>
<Paragraph>Do you think it will be ok?<Break />Otherwise let's consider a new vendor.</Paragraph>


 */

        public static void main(String[] args) {
            // String message = "Please tag @Bob Jones for approval!\n\n" // Paragraph
            //   + "Here's the max amount we were allotted:\n\n" // Paragraph
            //   + ">Max spend: $5000\n" // Start quote, line break
            //   + ">Max spend per user: $12\n" // Quote, line break
            //   + ">Target spend: $3000\n\n" // End quote
            //   + "Do you think it will be ok?\nOtherwise let's consider a new vendor."; // Paragraph with line break

            String message = "Please tag @Bob Jones for approval!\n\n" // Paragraph
                    + "Here's the max amount we were allotted:\n\n" // Paragraph
                    + ">Max spend: $5000\n" // Start quote, line break
                    + ">>Max spend per user: $12\n" // Quote, line break
                    + ">>Target spend: $3000\n\n" // End quote
                    + "Do you think it will be ok?\nOtherwise let's consider a new vendor."; // Paragraph with line break
            System.out.println(convert(message));
        }


        public static String convert(String str) {
            StringBuffer res = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            int quotes = 0;
            //boolean inQuote = false;
            for (int pos = 0; pos < str.length(); pos++) {
                char c = str.charAt(pos);
                if (c == '\n') {
                    if (pos < str.length()-1 && str.charAt(pos+1) == '\n') {
                        if (quotes > 0) {
                            sb.append(sb.toString());
                            for (int i = 0; i < quotes; i++) {
                                sb.append("</Quote>");
                            }
                            res.append(sb.toString());
                            sb = new StringBuffer();
                            //inQuote = false;
                            quotes = 0;
                        } else {
                            res.append("<Paragraph>");
                            res.append(sb.toString());
                            res.append("</Paragraph>");
                            sb = new StringBuffer();
                        }
                        pos++;
                    } else {
                        sb.append("<Break />");
                    }
                } else if (c == '>') {
                    int count = count(str, pos);
                    if (count > quotes) {
                        sb.append("<Quote>");
                        quotes = count;
                    }
                    pos += count-1;
        /*
        if (!inQuote) {
          inQuote = true;
          sb.append("<Quote>");
        }*/
                } else {
                    sb.append(c);
                }
            }
            res.append("<Paragraph>");
            res.append(sb.toString());
            res.append("</Paragraph>");
            return res.toString();
        }

        public static int count(String str, int pos) {
            int count = 0;
            while (pos < str.length() && str.charAt(pos) == '>') {
                count++;
                pos++;
            }
            return count;
        }

}

