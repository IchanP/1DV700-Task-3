package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

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
    // Attempt to read from the buffer
    try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        contentBuilder.append(currentLine).append("\n");
      }
    }
    this.contents = contentBuilder.toString();
  }

  public void WriteFileContents(String content) throws IOException {
    try (FileWriter writer = new FileWriter(this.file)) {
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
