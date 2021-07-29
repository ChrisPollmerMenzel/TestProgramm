package de.biddingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class BidResponse {

  private String id;
  private int bid;
  private String content;

  public static int compareByBid(BidResponse response1, BidResponse response2 ) {
    return Integer.compare(response1.getBid(), response2.getBid());
  }

}
