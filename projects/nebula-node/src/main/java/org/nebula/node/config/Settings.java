package org.nebula.node.config;

import org.apache.commons.cli.*;

import javax.annotation.Nonnull;

import static com.google.common.base.MoreObjects.firstNonNull;

/** Class to store settings for initializing the cluster */
public class Settings {
  private static final String MASTER_PORT = "master_port";
  private static final String MASTER_IP = "master_ip";
  private static final Integer DEFAULT_MASTER_PORT = 1100;

  private String masterIp;
  private Integer masterPort;
  private Options options;

  public Settings() {
    options = new Options();
    options
        .addOption("p", MASTER_PORT, true, "Port for master nebula service")
        .addOption("i", MASTER_IP, false, "IP for master nebula service");
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
    masterPort = firstNonNull(getIntValue(MASTER_PORT, line), DEFAULT_MASTER_PORT);
    masterIp = getStringValue(MASTER_IP, line);
  }

  public String getMasterIp() {
    return masterIp;
  }

  public Integer getMasterPort() {
    return masterPort;
  }

  public Options getOptions() {
    return options;
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
    formatter.printHelp("NebulaNode", options);
    System.exit(0);
  }
}
