package io.github.mwttg.tileoperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The first class.
 */
public class First {

  private static final Logger LOG = LoggerFactory.getLogger(First.class);

  /**
   * Add 3 to x.
   *
   * @param x the number where 3 is add to
   * @return the result
   */
  public static int add3(final int x) {
    LOG.debug("Incoming x = {}.", x);
    return x + 3;
  }

  /**
   * Main (only for testing the Logger).
   *
   * @param args the args
   */
  public static void main(String[] args) {
    add3(5);
  }
}
