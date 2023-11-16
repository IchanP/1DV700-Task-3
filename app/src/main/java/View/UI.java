package View;

public interface UI {
  
  public void Welcome();

  public String AskForFilePath();

  public String AskForEncryptionMethod();

  public int AskForKey();
}
