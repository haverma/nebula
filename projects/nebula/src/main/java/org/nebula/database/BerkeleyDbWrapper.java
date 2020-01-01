package org.nebula.database;

import com.sleepycat.je.*;
import org.nebula.datamodel.KeyValue;

import java.io.File;
import java.nio.charset.StandardCharsets;

/** Wrapper to do operations on the database */
public class BerkeleyDbWrapper {
  private Environment environment;
  private Database database;

  public BerkeleyDbWrapper() throws DatabaseException {
    EnvironmentConfig envConfig = new EnvironmentConfig();
    envConfig.setAllowCreate(true);
    File bdbFile = new File("/tmp/dbEnv");
    if (!bdbFile.exists()) {
      if (!bdbFile.mkdirs()) {
        throw new DatabaseException();
      }
    }
    environment = new Environment(bdbFile, envConfig);

    // Open the database, creating one if it does not exist
    DatabaseConfig dbConfig = new DatabaseConfig();
    dbConfig.setAllowCreate(true);
    database = environment.openDatabase(null, "KeyValueDatabase", dbConfig);
  }

  public boolean put(String key, String value) {
    try {
      database.put(
          null,
          new DatabaseEntry(key.getBytes(StandardCharsets.UTF_8)),
          new DatabaseEntry(value.getBytes(StandardCharsets.UTF_8)));
      return true;
    } catch (DatabaseException e) {
      // TODO: log warning
      return false;
    }
  }

  public KeyValue get(String key) {
    try {
      DatabaseEntry theKey = new DatabaseEntry(key.getBytes(StandardCharsets.UTF_8));
      DatabaseEntry theData = new DatabaseEntry();

      // Call get() to query the database
      if (database.get(null, theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {

        // Translate theData into a String.
        byte[] retData = theData.getData();
        return new KeyValue(key, new String(retData, StandardCharsets.UTF_8));
      }
      // TODO: log warning instead of system outs
      System.out.println("No record found with key '" + key + "'.");

    } catch (Exception e) {
      // TODO: Exception handling
    }
    return null;
  }

  public boolean delete(String key) {
    try {
      DatabaseEntry theKey = new DatabaseEntry(key.getBytes(StandardCharsets.UTF_8));
      // Delete the entry (or entries) with the given key
      database.delete(null, theKey);
      return true;
    } catch (Exception e) {
      // TODO: log warning
      return false;
    }
  }
}
