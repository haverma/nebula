package nebula.datamodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NebulaNodeMetadata {
  public static class KeyRange {
    private static final String PROP_MAX_KEY = "maxKey";
    private static final String PROP_MIN_KEY = "minKey";
    private final Integer maxKey;
    private final Integer minKey;

    @JsonCreator
    public KeyRange(
        @JsonProperty(PROP_MAX_KEY) Integer maxKey, @JsonProperty(PROP_MIN_KEY) Integer minKey) {
      this.maxKey = maxKey;
      this.minKey = minKey;
    }

    @JsonProperty(PROP_MAX_KEY)
    public Integer getMaxKey() {
      return maxKey;
    }

    @JsonProperty(PROP_MIN_KEY)
    public Integer getMinKey() {
      return minKey;
    }
  }

  private static final String PROP_IP = "ip";
  private static final String PROP_PORT = "port";
  private static final String PROP_KEY_RANGE = "keyRange";

  private String ip;
  private Integer port;
  private KeyRange keyRange;

  @JsonCreator
  public NebulaNodeMetadata(
      @JsonProperty(PROP_IP) String ip,
      @JsonProperty(PROP_PORT) Integer port,
      @JsonProperty(PROP_KEY_RANGE) KeyRange keyRange) {
    this.ip = ip;
    this.port = port;
    this.keyRange = keyRange;
  }

  @JsonProperty(PROP_IP)
  public String getIp() {
    return ip;
  }

  @JsonProperty(PROP_PORT)
  public Integer getPort() {
    return port;
  }

  @JsonProperty(PROP_KEY_RANGE)
  public KeyRange getKeyRange() {
    return keyRange;
  }
}
