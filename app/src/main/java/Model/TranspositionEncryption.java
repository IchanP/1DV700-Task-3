package Model;

public class TranspositionEncryption {

  PrintableUtil printable = new PrintableUtil();

  public String Encrypt(String plainText, int key) {

    String paddedText = PadTextWithSpaces(plainText, key);
    StringBuilder encrypted = this.EncryptText(paddedText, key);
    return encrypted.toString();
  }

  private StringBuilder EncryptText(String unEncryptedText, int key) {
    StringBuilder encrypted = new StringBuilder();
    int textLength = unEncryptedText.length();
    // Outer loop dictates which column is being worked on.
    for (int i = 0; i < key; i++) {
      // Inner loop dictates which position in the column is being worked on.
      for (int j = i; j < textLength; j += key) {
        encrypted.append(unEncryptedText.charAt(j));
      }
    }
    return encrypted;
  }

  private String PadTextWithSpaces(String text, int key) {
    String cleanText = text.toString();
    int textLength = cleanText.length();
    // The text is passed with spaces so that the text can be neatly divided into columns
    // paddingLength is essentially the number of spaces required to fill out the last column of the
    // grid with characters.
    int paddingLength = key - (textLength % key);
    if (paddingLength != key) {
      for (int i = 0; i < paddingLength; i++) {
        cleanText += " "; // Padding with spaces
      }
      textLength = cleanText.length();
    }
    return cleanText;
  }

  // Method to decrypt the text encrypted using columnar transposition
  public static String Decrypt(String text, int key) {
    StringBuilder decrypted = new StringBuilder();
    int numRows = text.length() / key;

    // Outer loop works on the rows.
    for (int i = 0; i < numRows; i++) {
      // Inner loop works on the columns
      for (int j = 0; j < key; j++) {
        // The charposition is calculated based on the row and column positions.
        // Where the column number is multiplied by the number of rows and the row number is added.
        int charPosition = j * numRows + i;
        if (charPosition < text.length()) {
          decrypted.append(text.charAt(charPosition));
        }
      }
    }
    // Return the decrypted string, trimmed to remove any padding spaces
    return decrypted.toString().trim();
  }
}
