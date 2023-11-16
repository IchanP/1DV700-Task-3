package Controller;

import java.io.IOException;
import Model.FileManager;
import View.UI;

public class CryptoController {

  private UI v;
  private FileManager fm = new FileManager();

  public CryptoController(UI v) {
    this.v = v;
  }

  public void start() {
    this.v.Welcome();
    this.GetFileContents();
  }

  private String GetFileContents() {
    String filePath = this.v.AskForFilePath();
    String contents = this.ExtractFileContent(filePath);
    System.out.println(contents);
    return filePath;
  }

  private String ExtractFileContent(String filePath) {
    try {
      return fm.extractFileContents(filePath);
    } catch (IllegalArgumentException | IOException e) {
      System.out.println(e.getMessage());
      return this.GetFileContents();
    }
  }
}
