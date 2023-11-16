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
    System.out.println(
        "Please enter the path of the file you want to encrypt/decrypt. You may use either relative or absolute paths:");
    String filePath = scanner.nextLine();
    return filePath;
  }

  public EncryptionMethod AskForEncryptionMethod() {
    System.out.println(
        "What encryption method? Enter either \"s\" for substitution or \"t\" for transposition:");
    String method = scanner.nextLine();
    switch (method) {
      case "s":
        return EncryptionMethod.SUBSTITUTION;
      case "t":
        return EncryptionMethod.TRANSPOSITION;
      default:
        throw new IllegalArgumentException("Invalid selection.");
    }
  }

  public DecryptEncrypt AskForDecryptEncrypt() {
    System.out.println("Do you want to encrypt or decrypt? Enter either \"e\" or \"d\":");
    String method = scanner.nextLine();
    switch (method) {
      case "e":
        return DecryptEncrypt.ENCRYPT;
      case "d":
        return DecryptEncrypt.DECRYPT;
      default:
        throw new IllegalArgumentException("Invalid selection.");
    }
  }

  public int AskForSubKey() {
    System.out.println("Please enter the key you want to use, within 8 bits (0-255):");
    int key = scanner.nextInt();
    if (key > 255 || key < 0) {
      throw new IllegalArgumentException("Key must be within 8 bits (0-255).");
    }
    return key;
  }

  public String AskForTransKey() {
    System.out.println("Please enter the key you want to use, TODO"); // TODO
    return "lol";
  }

  public void PrintError(String message) {
    System.out.println(message);
  }
}
