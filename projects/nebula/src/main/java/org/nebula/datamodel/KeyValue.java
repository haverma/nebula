package org.nebula.datamodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Container class for key and value pair */
public class KeyValue {
  private static final String PROP_KEY = "key";
  private static final String PROP_VALUE = "value";

  private String key;
  private String value;

  @JsonCreator
  public KeyValue(@JsonProperty(PROP_KEY) String key, @JsonProperty(PROP_VALUE) String value) {
    this.key = key;
    this.value = value;
  }

  @JsonProperty(PROP_KEY)
  public String getKey() {
    return key;
  }

  @JsonProperty(PROP_VALUE)
  public String getValue() {
    return value;
  }
}
