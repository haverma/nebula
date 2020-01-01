package org.nebula.config;

import org.apache.commons.cli.*;

import javax.annotation.Nonnull;

import static com.google.common.base.MoreObjects.firstNonNull;

/** Class to store settings for initializing the cluster */
public class Settings {
  private static final String PORT = "port";
  private static final String STORAGE_TYPE = "storage_type";

  public enum StorageType {
    BDB,
    FILE_SYSTEM;
  }

  private Integer storageServicePort;
  private StorageType storageType;
  private Options options;

  public Settings() {
    options = new Options();
    options
        .addOption("p", PORT, true, "Port for the Nebula storage service")
        .addOption("t", STORAGE_TYPE, false, "Type of storage");
  }

  public void parse(String[] args) {
    CommandLineParser parser = new DefaultParser();
    CommandLine line = null;
    try {
      line = parser.parse(options, args);
    } catch (ParseException e) {
      help();
    }
    assert line != null;
    storageServicePort = firstNonNull(getIntValue(PORT, line), 2501);
    storageType = convertStorageType(firstNonNull(getStringValue(STORAGE_TYPE, line), "bdb"));
  }

  public Integer getStorageServicePort() {
    return storageServicePort;
  }

  public void setStorageServicePort(Integer storageServicePort) {
    this.storageServicePort = storageServicePort;
  }

  public StorageType getStorageType() {
    return storageType;
  }

  public void setStorageType(StorageType storageType) {
    this.storageType = storageType;
  }

  public Options getOptions() {
    return options;
  }

  private StorageType convertStorageType(String storageType) {
    StorageType val = null;
    switch (storageType) {
      case "bdb":
        val = StorageType.BDB;
        break;
      case "file_system":
        val = StorageType.FILE_SYSTEM;
        break;
      default:
        // TODO: log warning for invalid storage type before setting a default
        val = StorageType.BDB;
    }
    return val;
  }

  private Integer getIntValue(@Nonnull String optionName, @Nonnull CommandLine cmd) {
    if (cmd.hasOption(optionName)) {
      try {
        return Integer.parseInt(cmd.getOptionValue(optionName));
      } catch (NumberFormatException e) {
        // TODO: add logging for errors
        return null;
      }
    }
    return null;
  }

  private String getStringValue(@Nonnull String optionName, @Nonnull CommandLine cmd) {
    if (cmd.hasOption(optionName)) {
      return cmd.getOptionValue(optionName);
    }
    return null;
  }

  private void help() {
    // print help
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("NebulaService", options);
    System.exit(0);
  }
}
