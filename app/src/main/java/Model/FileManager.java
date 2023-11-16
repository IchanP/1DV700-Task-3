package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class FileManager {

  private String filePath;
  private String contents;
  private File file;

  public String ExtractFileContents(String filePath) throws FileNotFoundException, IOException {
    this.filePath = filePath;
    this.file = new File(this.filePath);
    this.CheckFileExists(file);
    this.CheckFileIsTxt(file);
    this.ReadFileContents();
    return this.contents;
  }

  public void ReadFileContents() throws FileNotFoundException, IOException {
    StringBuilder contentBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(new FileInputStream(this.file), StandardCharsets.UTF_8))) {
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        contentBuilder.append(currentLine).append("\n");
      }
    }
    this.contents = contentBuilder.toString();
  }

  public void WriteFileContents(String content) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(this.file), StandardCharsets.UTF_8))) {
      writer.write(content);
    }
    this.contents = content;
  }

  private void CheckFileExists(File file) {
    if (!file.exists()) {
      throw new IllegalArgumentException("File does not exist.");
    }
  }

  private void CheckFileIsTxt(File file) {
    if (!file.getName().toLowerCase().endsWith(".txt")) {
      throw new IllegalArgumentException("File is not a .txt file.");
    }
  }
}
