package View;
import java.util.Scanner;
import java.io.File;
public class ConsoleUI implements UI {
  Scanner scanner = new Scanner(System.in);

  public void Welcome() {
    System.out.println("Welcome to encryption/decryption program.");
  }

  public String AskForFilePath() {
    System.out.println("Current working directory: " + new File(".").getAbsolutePath());
    System.out.println("Please enter the path of the file you want to encrypt/decrypt. You may use either relative or absolute paths:");
    String filePath = scanner.nextLine();
    return filePath;
  }

  public String AskForEncryptionMethod() {
    System.out.println("Please enter the encryption method you want to use:");
    return "PLACEHOLDER";
  }

  public int AskForKey() {
    System.out.println("Please enter the key you want to use:");
    return 0;
  }

  public void PrintError(String message) {
    System.out.println(message);
  }
}
