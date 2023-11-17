package View;

public interface UI {
  enum EncryptionMethod {
    SUBSTITUTION, TRANSPOSITION
  }
  enum DecryptEncrypt {
    DECRYPT, ENCRYPT
  }

  public void Welcome();

  public String AskForFilePath();

  public EncryptionMethod AskForEncryptionMethod();

  public DecryptEncrypt AskForDecryptEncrypt();

  public int AskForSubKey();

  public int AskForTransKey();
}
