package Model;

public class SubstitutionEncryption {

  public String Encrypt(int key, String content) {
    StringBuilder encryptedContent = new StringBuilder(); // Use stringbuilder to ensure that
                                                          // linebreaks remain consistent.
    for (int i = 0; i < content.length(); i++) {
      encryptedContent.append(EncryptChar(key, content.charAt(i), i));
    }
    return encryptedContent.toString();
  }

  public String Decrypt(int key, String content) {

    return "X";
  }

  private char EncryptChar(int key, char c, int position) {
    if (c == '\n' || c == '\r') {
      return c; // Skip encryption for line breaks
    }
    int shift = position % 2 == 0 ? key : -key; // Alternate shift direction
    // if position is even it will shift it forward. If odd it will shift backwards.
    int encryptExtendedASCII = ((int) c + shift + 256) % 256; // Applies the shift.
    // The % 256 modolo ensures that the number is always between 0 and 255.
    // the shift determines whether it will be shifted forward or backwards.
    // the 256 is added to ensure that the number is positive, as it could be negative because the
    // shift may be negative.
    return (char) encryptExtendedASCII;
  }
}
