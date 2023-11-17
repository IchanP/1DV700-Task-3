package Model;

public class SubstitutionEncryption {

  PrintableUtil printable = new PrintableUtil();

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
    if (printable.IsNonPrintable(c)) { // Skip non-printable and extended ASCII characters
      return c;
    }
    int shift = position % 2 == 0 ? key : -key;
    // If the character is on an even position shift forward
    // Otherwise shift back
    int shiftedValue = c + shift;
    // Adjust for wrapping within printable range
    int shiftedValueWithinRange = this.ShiftValueWithinRange(shiftedValue);
    return (char) shiftedValueWithinRange;
  }

  private char DecryptChar(int key, char c, int position) {
    if (printable.IsNonPrintable(c)) { // Skip non-printable and extended ASCII characters
      return c;
    }
    int shift = position % 2 == 0 ? -key : key;
    // If the character is on an odd position shift backwards
    // Otherwise shift forward
    int shiftedValue = c + shift;
    // Adjust for wrapping within printable range
    int shiftedValueWithinRange = this.ShiftValueWithinRange(shiftedValue);
    return (char) shiftedValueWithinRange;
  }

  private int ShiftValueWithinRange(int valueToShift) {
    int shiftedValue = valueToShift;

    int rangeStart = 32;
    int rangeEnd = 126;

    int range = 126 - 32 + 1; // Count of printable characters.
    // Shifts the value down below the upper
    // limit of
    // the printable range. Possible values are 33-126.
    // For example if the value is 127, it will shift
    // it back down 32.
    if (valueToShift > rangeEnd) {
      shiftedValue = rangeStart + (valueToShift - rangeEnd - 1) % range;
    } else if (valueToShift < rangeStart) {
      // Shifts the value up above the lower limit
      // of the
      // printable range. Possible values are 95-126.
      // For example if the value is 31, it will shift
      // it up to 126.
      shiftedValue = rangeEnd - (rangeStart - valueToShift - 1) % range;
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
