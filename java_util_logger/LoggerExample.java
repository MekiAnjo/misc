package java_util_logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerExample {

  private static final Logger LOGGER = Logger.getLogger(
    LoggerExample.class.getName()
  );

  public static void main(String[] args) {
    LOGGER.info("Logger Name: " + LOGGER.getName());

    LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");

    // An array of size 3
    int[] a = { 1, 2, 3 };
    int index = 4;
    LOGGER.config("Index is set to " + index);
    LOGGER.log(Level.CONFIG,"Index is set to {0}", index);
    LOGGER.log(Level.CONFIG,"Index is set to {0}", new Object[] { index });
    LOGGER.config(() -> "Index is set to " + index);

    try {
      System.out.println(a[index]);
    } catch (ArrayIndexOutOfBoundsException e) {
      LOGGER.log(
        java.util.logging.Level.SEVERE,
        "ArrayIndexOutOfBoundsException",
        e
      );
    }
  }
}
