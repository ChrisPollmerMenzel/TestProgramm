package de.biddingservice.api;

import de.biddingservice.entity.BidRequest;
import de.biddingservice.service.BiddingService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BiddingController {

  @Autowired
  private BiddingService biddingService;

  @RequestMapping(value = "/bidders", method=RequestMethod.POST, produces="application/json")
  public void setBidders(@RequestBody List<String> bidders) {

    biddingService.setBiddingConfig(bidders);
  }

  @RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
  public String bidForAuction(@PathVariable("id") final String id,
      @RequestParam Map<String, String> attributes) {

    BidRequest bidRequest = new BidRequest(id, attributes);
    return biddingService.bidForAuction(bidRequest);
  }

}
