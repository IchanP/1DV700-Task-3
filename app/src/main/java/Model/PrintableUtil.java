package Model;

public class PrintableUtil {
  
  public boolean IsNonPrintable(char c) {
    return c < 32 || c > 126; // These correspond to the non-printable ASCII characters, which if
                              // encrypted and encoded as UTF_8 will not be observable.
                              // They can still be encrypted and decrypted without being observable.
                              // However for the sake of the assignment I have chosen to skip them.
  }
}
