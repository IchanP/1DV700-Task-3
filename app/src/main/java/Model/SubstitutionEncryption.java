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
    if (this.IsNonPrintable(c)) {
      return c;
    }
    int shift = position % 2 == 0 ? key : -key; // Alternate shift direction
    // if position is even it will shift it forward. If odd it will shift backwards.
    int firstShift = c + shift;

    // Adjust for wrapping within printable range
    int lastShift = this.ShiftValueWithinRange(firstShift);

    return (char) lastShift;
  }

  private char DecryptChar(int key, char c, int position) {
    if (this.IsNonPrintable(c)) {
      return c;
    }
    int shift = position % 2 == 0 ? -key : key; // Alternate shift direction
    // does the reverse of the encryption. if it's even it will shift it backwards, if odd it will
    // shift it forward.
    int firstShift = c + shift;
    int lastShift = this.ShiftValueWithinRange(firstShift);
    return (char) lastShift;
  }

  private int ShiftValueWithinRange(int valueToShift) {
    int shiftedValue = valueToShift;
    if (valueToShift > 126) {
      shiftedValue = 32 + (valueToShift - 127); // Shifts the value down below the upper limit of
                                                // the printable range. Possible values are 33-126.
                                                // For example if the value is 127, it will shift
                                                // it back down 33.
    } else if (valueToShift < 32) {
      shiftedValue = 127 - (32 - valueToShift); // Shifts the value up above the lower limit of the
                                                // printable range. Possible values are 95-126.
                                                // For example if the value is 31, it will shift
                                                // it up to 126.
    }
    return shiftedValue;
  }

  private boolean IsNonPrintable(char c) {
    return c < 32 || c > 126; // These correspond to the non-printable ASCII characters, which if
                              // encrypted and encoded as UTF_8 will not be observable.
                              // They can still be encrypted and decrypted.
                              // However for the sake of the assignment I have chosen to skip them.
  }
}
