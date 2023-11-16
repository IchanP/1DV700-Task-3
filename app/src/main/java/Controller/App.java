package Controller;

import View.ConsoleUI;
import View.UI;

public class App {

  public static void main(String[] args) {
    UI view = new ConsoleUI();
    CryptoController controller = new CryptoController(view);
    controller.start();
  }
}
