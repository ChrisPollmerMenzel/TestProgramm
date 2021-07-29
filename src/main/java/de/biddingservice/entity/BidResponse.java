package de.biddingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Response of bid.
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class BidResponse {

  //id
  private String id;
  // bid value
  private int bid;
  // content String
  private String content;

  // compare bids
  public static int compareByBid(BidResponse response1, BidResponse response2 ) {
    return Integer.compare(response1.getBid(), response2.getBid());
  }

}
