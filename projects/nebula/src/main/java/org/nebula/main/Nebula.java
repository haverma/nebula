package org.nebula.main;

import org.nebula.config.Settings;
import org.nebula.database.BerkeleyDbWrapper;

public class Nebula {
  private Settings settings;
  private BerkeleyDbWrapper dbWrapper;

  public Nebula(BerkeleyDbWrapper database, Settings settings) {
    this.settings = settings;
    this.dbWrapper = database;
  }

  public Settings getSettings() {
    return settings;
  }

  public BerkeleyDbWrapper getDbWrapper() {
    return dbWrapper;
  }
}
