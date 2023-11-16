package Controller;

import java.io.IOException;
import Model.FileManager;
import Model.SubstitutionEncryption;
import View.UI;
import View.UI.EncryptionMethod;
import View.UI.DecryptEncrypt;

public class CryptoController {

  private UI v;
  private FileManager fm = new FileManager();

  public CryptoController(UI v) {
    this.v = v;
  }

  public void start() {
    this.v.Welcome();
    char x = (char) 250;
    System.out.println(x);
    String contents = this.GetFileContents();
    View.UI.EncryptionMethod method = this.GetEncryptionMethod();
    View.UI.DecryptEncrypt decryptEncrypt = this.GetEncryptDecrypt();
    if (method == EncryptionMethod.SUBSTITUTION) {
      this.DoSubstitution(decryptEncrypt, contents);
    } else {
      this.DoTransposition(decryptEncrypt, contents);
    }
  }

  private String GetFileContents() {
    String filePath = this.v.AskForFilePath();
    String contents = this.ExtractFileContent(filePath);
    return contents;
  }

  private String ExtractFileContent(String filePath) {
    try {
      return fm.ExtractFileContents(filePath);
    } catch (IllegalArgumentException | IOException e) {
      System.out.println(e.getMessage());
      return this.GetFileContents();
    }
  }

  private EncryptionMethod GetEncryptionMethod() {
    try {
      return v.AskForEncryptionMethod();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return this.GetEncryptionMethod();
    }
  }

  private DecryptEncrypt GetEncryptDecrypt() {
    try {
      return v.AskForDecryptEncrypt();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return this.GetEncryptDecrypt();
    }
  }

  private int GetSubKey() {
    try {
      return v.AskForSubKey();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return this.GetSubKey();
    }
  }

  private void DoSubstitution(DecryptEncrypt method, String contents) {
    int key = this.GetSubKey();
    SubstitutionEncryption se = new SubstitutionEncryption();
    if (method == DecryptEncrypt.ENCRYPT) {
      String encryptedMessage = se.Encrypt(key, contents);
      this.WriteToFile(encryptedMessage);
    } else {
      // TODO decrypt substitution
    }
  }

  // TODO add transposition key getter here

  private void DoTransposition(DecryptEncrypt method, String contents) {
    String key = this.v.AskForTransKey();
    if (method == DecryptEncrypt.ENCRYPT) {
      // TODO encrypt transposition
    } else {
      // TODO decrypt transposition
    }
  }

  private void WriteToFile(String contents) {
    try {
      fm.WriteFileContents(contents);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
