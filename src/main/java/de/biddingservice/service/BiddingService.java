package de.biddingservice.service;

import de.biddingservice.entity.BidRequest;
import java.util.List;

/**
 * Service for Bidding.
 */
public interface BiddingService {

  /**
   * Save URLs of bidders.
   * @param biddingConfigList URL List
   */
  void setBiddingConfig(List<String> biddingConfigList);

  /**
   * Auction Method.
   *
   * @param bidRequest Requestparameter for auction
   * @return winner of auction
   */
  String bidForAuction(BidRequest bidRequest);

}
