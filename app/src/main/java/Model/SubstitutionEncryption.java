package Model;

public class SubstitutionEncryption {

  public String Encrypt(int key, String content) {
    StringBuilder cipherText = new StringBuilder(); // Use stringbuilder to ensure that
                                                    // linebreaks remain consistent.
    for (int i = 0; i < content.length(); i++) {
      cipherText.append(EncryptChar(key, content.charAt(i), i));
    }
    return cipherText.toString();
  }

  public String Decrypt(int key, String content) {
    StringBuilder plaintext = new StringBuilder();
    for (int i = 0; i < content.length(); i++) {
      // Avoid encrypting the end of file marker.
      if (i == content.length() - 1) {
        break;
      }
      plaintext.append(DecryptChar(key, content.charAt(i), i));
    }
    return plaintext.toString();
  }


  private char EncryptChar(int key, char c, int position) {
    if (c < 32 || c > 126) { // Skip non-printable and extended ASCII characters
      return c;
    }
    int shift = position % 2 == 0 ? key : -key;
    int shiftedValue = c + shift;
    int range = 126 - 32 + 1; // Range of printable ASCII characters

    // Adjust for wrapping within printable range
    if (shiftedValue > 126) {
      shiftedValue = 32 + (shiftedValue - 127);
    } else if (shiftedValue < 32) {
      shiftedValue = 127 - (32 - shiftedValue);
    }

    return (char) shiftedValue;
  }

  private char DecryptChar(int key, char c, int position) {
    if (c < 32 || c > 126) { // Skip non-printable and extended ASCII characters
      return c;
    }
    int shift = position % 2 == 0 ? -key : key;
    int shiftedValue = c + shift;
    int range = 126 - 32 + 1; // Range of printable ASCII characters

    // Adjust for wrapping within printable range
    if (shiftedValue > 126) {
      shiftedValue = 32 + (shiftedValue - 127);
    } else if (shiftedValue < 32) {
      shiftedValue = 127 - (32 - shiftedValue);
    }

    return (char) shiftedValue;
  }


  /*
   * private char EncryptChar(int key, char c, int position) { int shift = position % 2 == 0 ? key :
   * -key; // Alternate shift direction // if position is even it will shift it forward. If odd it
   * will shift backwards. int encryptExtendedASCII = ((int) c + shift + 127) % 127; // Applies the
   * shift. // The % 256 modolo ensures that the number is always between 0 and 255. // the shift
   * determines whether it will be shifted forward or backwards. // the 256 is added to ensure that
   * the number is positive, as it could be negative because the // shift may be negative. return
   * (char) encryptExtendedASCII; }
   * 
   * private char DecryptChar(int key, char c, int position) { int shift = position % 2 == 0 ? -key
   * : key; // Alternate shift direction // does the reverse of the above. if it's even it will
   * shift it backwards, if odd it will shift // it forward int plaintextExtendedASCII = ((int) c +
   * shift + 127) % 127; // This is the same as above, but the shift is reversed from the encryption
   * due to the line before. return (char) plaintextExtendedASCII; }
   */
}
