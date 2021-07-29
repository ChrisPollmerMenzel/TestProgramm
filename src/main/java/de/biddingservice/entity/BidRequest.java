package de.biddingservice.entity;

import java.io.Serializable;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Request for bidding.
 */
@AllArgsConstructor
@Getter
public class BidRequest implements Serializable {

  // id
  private final String id;
  // parameter for bidding
  private final Map<String, String> attributes;
}
