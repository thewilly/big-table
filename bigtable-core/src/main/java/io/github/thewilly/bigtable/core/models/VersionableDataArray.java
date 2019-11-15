package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.Collection;

/**
 * The interface Versionable data.
 *
 * @param <T> the type parameter
 */
public interface VersionableDataArray<T extends Comparable<T>> extends Serializable {

  /** The constant DEFAULT_NUMBER_OF_VERSIONS. */
  int DEFAULT_NUMBER_OF_VERSIONS = 100;

  /**
   * Gets all versions.
   *
   * @return the all versions
   */
  Collection<VersionableData<T>> getAllVersions();

  /**
   * Gets most recent version.
   *
   * @return the most recent version
   */
  VersionableData<T> getMostRecentVersion();

  /**
   * Gets number of versions.
   *
   * @return the number of versions
   */
  int getNumberOfVersionsStored();

  /**
   * Add version.
   *
   * @param newVersion the new version
   * @return true if added, false otherwise.
   */
  boolean addVersion(VersionableData<T> newVersion);
}
