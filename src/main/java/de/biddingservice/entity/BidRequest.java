package de.biddingservice.entity;

import java.io.Serializable;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BidRequest implements Serializable {

  private final String id;
  private final Map<String, String> attributes;
}
