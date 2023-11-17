package Model;

public class TranspositionEncryption {



  // Function to encrypt the text
  public String Encrypt(String text, int key) {
    StringBuilder encrypted = new StringBuilder();
    int len = text.length();

    // Create columns
    for (int i = 0; i < key; i++) {
      int index = i;
      while (index < len) {
        encrypted.append(text.charAt(index));
        index += key;
      }
    }

    return encrypted.toString();
  }

  public String Decrypt(String encryptedText, int key) {
    int len = encryptedText.length();

    // Calculate the number of full rows and the number of characters in the last row
    int fullRows = len / key;
    int extraChars = len % key;

    // Create an array to store the decrypted characters
    char[] decrypted = new char[len];

    // Initialize variables to track the current position in the encrypted text
    int encryptedIndex = 0;

    // Iterate over each column in the grid
    for (int col = 0; col < key; col++) {
      int index = col;
      int numberOfRows = col < extraChars ? fullRows + 1 : fullRows;

      // Iterate over each row for this column
      for (int row = 0; row < numberOfRows; row++) {
        decrypted[index] = encryptedText.charAt(encryptedIndex++);
        index += key;
      }
    }

    // Convert the character array to a string
    return new String(decrypted);
  }


}
