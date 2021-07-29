package de.biddingservice.service.impl;

import de.biddingservice.entity.BidRequest;
import de.biddingservice.entity.BidResponse;
import de.biddingservice.entity.ConfigUrl;
import de.biddingservice.repository.BiddingConfigRepository;
import de.biddingservice.service.BiddingService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BiddingServiceImpl implements BiddingService {

  @Autowired
  private BiddingConfigRepository biddingConfigRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public void setBiddingConfig(List<String> biddingConfigList) {

    biddingConfigRepository.deleteAll();
    for (String url: biddingConfigList) {
      ConfigUrl configUrl = new ConfigUrl(url);
      biddingConfigRepository.save(configUrl);
    }
  }

  @Override
  public String bidForAuction(BidRequest bidRequest) {

    List<BidResponse> bidResponseList = getResponseFromUrls(bidRequest);

    Optional<BidResponse> auctionWinner = bidResponseList.stream().max(BidResponse::compareByBid);

    return getAuctionWinner(auctionWinner);
  }

  private List<BidResponse> getResponseFromUrls(BidRequest bidRequest) {

    List<ConfigUrl> configList = biddingConfigRepository.findAll();
    List<BidResponse> bidResponseList = new ArrayList<>();
    for (ConfigUrl configUrl : configList) {
      bidResponseList.add(restTemplate.postForObject(configUrl.getUrl(), bidRequest, BidResponse.class));
    }
    return bidResponseList;
  }

  private String getAuctionWinner(Optional<BidResponse> auctionWinner) {

    if (auctionWinner.isPresent()) {
      String win = auctionWinner.get().getContent();
      win = win.replace( "$price$", String.valueOf(auctionWinner.get().getBid()));
      log.info(win);
      return win;
    }
    return null;
  }
}
