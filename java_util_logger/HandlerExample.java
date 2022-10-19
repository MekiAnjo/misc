package java_util_logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlerExample {

  public static final Logger LOGGER = Logger.getLogger(
    HandlerExample.class.getName()
  );

  public static void main(String[] args) {
    Handler consoleHandler = null;
    Handler fileHandler = null;

    try {
      //      Creating consoleHandler and fileHandler
      consoleHandler = new ConsoleHandler();
      fileHandler = new FileHandler("./java_util_logger/log.log");

      //      Assigning handlers to LOGGER object
      LOGGER.addHandler(consoleHandler);
      LOGGER.addHandler(fileHandler);

      //      Setting levels to handlers and LOGGER
      consoleHandler.setLevel(Level.ALL);
      fileHandler.setLevel(Level.ALL);
      LOGGER.setLevel(Level.ALL);

      LOGGER.config("Configuration done.");

      //      Console handler removed
      LOGGER.removeHandler(consoleHandler);

      LOGGER.log(Level.FINE, "Finer logged");
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "error occur in FileHandler.", e);
    }

    LOGGER.finer("Finest example or LOGGER handler completed.");
  }
}
