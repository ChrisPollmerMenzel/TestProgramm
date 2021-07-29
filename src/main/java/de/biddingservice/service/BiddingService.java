package de.biddingservice.service;

import de.biddingservice.entity.BidRequest;
import java.util.List;

public interface BiddingService {

  void setBiddingConfig(List<String> biddingConfigList);

  String bidForAuction(BidRequest bidRequest);

}
