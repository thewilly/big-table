package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;

/** The interface Data version. */
public interface VersionableData extends Serializable {

  /**
   * Is valid boolean. True if the data version is the most recent one, false otherwise.
   *
   * @return the boolean value that represents whether the data version is valid (most recent one)
   *     or not.
   */
  boolean isValid();

  /** Invalidates the data version so it's no longer the most recent one available. */
  void invalidate();

  /**
   * Gets timestampt that represents when the data was created.
   *
   * @return the timestampt when the data version was created.
   */
  long getTimestampt();

  /**
   * Gets the actual value of the version of the data.
   *
   * @return the the value of the version of the data.
   */
  byte[] get();
}
