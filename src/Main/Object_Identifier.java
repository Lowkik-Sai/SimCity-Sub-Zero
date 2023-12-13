package Main;

public class Object_Identifier {

    public String getUnicode(String emoji) {
        if (emoji == null || emoji.isEmpty()) {
            return null; // or throw an exception, depending on your requirements
        }

        // Convert the Unicode string to an array of integers
        int[] codePoints = emoji.codePoints().toArray();

        // Convert each code point to its hexadecimal representation
        StringBuilder unicodeStringBuilder = new StringBuilder();
        for (int codePoint : codePoints) {
            String unicode = String.format("\\u%04x", codePoint);
            unicodeStringBuilder.append(unicode);
        }

        // Convert the accumulated Unicode string to integer
        int unicodeAsInt = codePoints[0]; // Assuming emoji consists of a single code point

        // Format the integer as a hexadecimal string with "0x" prefix
        String unicodeAsString = String.format("0x%04X", unicodeAsInt);

        return unicodeAsString;
    }
}
