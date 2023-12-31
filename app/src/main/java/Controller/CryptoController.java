package Controller;

import java.io.IOException;
import Model.FileManager;
import Model.SubstitutionEncryption;
import Model.TranspositionEncryption;
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
      String substitutionCipherText = se.Encrypt(key, contents);
      this.WriteToFile(substitutionCipherText);
    } else {
      String substitutionPlainText = se.Decrypt(key, contents);
      this.WriteToFile(substitutionPlainText);
    }
  }

  // TODO add transposition key getter here

  private void DoTransposition(DecryptEncrypt method, String contents) {
    int key = this.v.AskForTransKey();
    TranspositionEncryption te = new TranspositionEncryption();
    if (method == DecryptEncrypt.ENCRYPT) {
      String transpositionCipherText = te.Encrypt(contents, key);
      this.WriteToFile(transpositionCipherText);
    } else {
      String transpositionPlainText = te.Decrypt(contents, key);
      this.WriteToFile(transpositionPlainText);
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
