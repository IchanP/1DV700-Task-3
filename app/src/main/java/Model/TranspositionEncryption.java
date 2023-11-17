package Model;

public class TranspositionEncryption {

  PrintableUtil printable = new PrintableUtil();

  // Method to encrypt using a simple columnar transposition
  public String Encrypt(String text, int columnWidth) {
    StringBuilder filteredText = new StringBuilder();
    StringBuilder encrypted = new StringBuilder();

    // Filter out non-printable characters
    for (char c : text.toCharArray()) {
      if (!printable.IsNonPrintable(c)) {
        filteredText.append(c);
      }
    }

    String cleanText = filteredText.toString();
    int textLength = cleanText.length();

    // Pad the text with spaces if it's not a multiple of the column width
    int paddingLength = columnWidth - (textLength % columnWidth);
    if (paddingLength != columnWidth) {
      for (int i = 0; i < paddingLength; i++) {
        cleanText += " ";
      }
      textLength = cleanText.length();
    }

    // Read the characters column-wise
    for (int i = 0; i < columnWidth; i++) {
      for (int j = i; j < textLength; j += columnWidth) {
        encrypted.append(cleanText.charAt(j));
      }
    }

    return encrypted.toString();
  }

  // Method to decrypt the text encrypted using columnar transposition
  public static String Decrypt(String text, int columnWidth) {
    StringBuilder decrypted = new StringBuilder();
    int numRows = text.length() / columnWidth;

    // Reconstruct the grid used for the encryption and read row-wise
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < columnWidth; j++) {
        int charPosition = j * numRows + i;
        if (charPosition < text.length()) {
          decrypted.append(text.charAt(charPosition));
        }
      }
    }

    return decrypted.toString().trim(); // trim to remove any padding
  }

}
