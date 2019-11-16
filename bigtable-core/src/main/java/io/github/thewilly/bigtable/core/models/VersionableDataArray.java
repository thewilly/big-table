package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Stream;

/** The interface Versionable data array. */
public interface VersionableDataArray extends Serializable {

  /** The constant DEFAULT_NUMBER_OF_VERSIONS. */
  int DEFAULT_NUMBER_OF_VERSIONS = 100;

  /**
   * Gets all versions.
   *
   * @return the all versions
   */
  Stream<VersionableData> getAllVersions();

  /**
   * Gets most recent version.
   *
   * @return the most recent version
   */
  VersionableData getMostRecentVersion();

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
   * @return null if no value was present or the value of the previous version.
   */
  VersionableData addVersion(VersionableData newVersion);
}
