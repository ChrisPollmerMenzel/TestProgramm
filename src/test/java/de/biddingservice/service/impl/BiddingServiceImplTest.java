package de.biddingservice.service.impl;

import de.biddingservice.entity.BidRequest;
import de.biddingservice.entity.BidResponse;
import de.biddingservice.entity.ConfigUrl;
import de.biddingservice.repository.BiddingConfigRepository;
import de.biddingservice.service.BiddingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BiddingServiceImplTest {

  @Autowired
  private BiddingService biddingService;

  @MockBean
  private BiddingConfigRepository repository;

  @MockBean
  private RestTemplate restTemplate;

  @Test
  void setBiddingConfig() {

    List<String> configUrlList = new ArrayList<>();
    configUrlList.add("http1");
    configUrlList.add("http2");
    configUrlList.add("http3");

    biddingService.setBiddingConfig(configUrlList);

    verify(repository, times(1)).deleteAll();
    verify(repository, times(3)).save(any());
  }

  @Test
  void bidForAuction() {
    Map<String, String> attributes = new HashMap<>();
    attributes.put("a", "5");
    attributes.put("b", "6");
    BidRequest bidRequest = new BidRequest("4", attributes);

    List<ConfigUrl> configUrlList = generateConfigUrlList();
    when(repository.findAll()).thenReturn(configUrlList);

    BidResponse bidResponse1 = new BidResponse("4", 150, "a:$price$");
    when(restTemplate.postForObject(configUrlList.get(0).getUrl(), bidRequest, BidResponse.class))
        .thenReturn(bidResponse1);

    BidResponse bidResponse2 = new BidResponse("4", 100, "b:$price$");
    when(restTemplate.postForObject(configUrlList.get(1).getUrl(), bidRequest, BidResponse.class))
        .thenReturn(bidResponse2);

    BidResponse bidResponse3 = new BidResponse("4", 120, "c:$price$");
    when(restTemplate.postForObject(configUrlList.get(2).getUrl(), bidRequest, BidResponse.class))
        .thenReturn(bidResponse3);

    String response = biddingService.bidForAuction(bidRequest);

    assertEquals("a:150", response);
  }

  private List<ConfigUrl> generateConfigUrlList() {

    List<ConfigUrl> configUrlList = new ArrayList<>();
    configUrlList.add(new ConfigUrl("http1"));
    configUrlList.add(new ConfigUrl("http2"));
    configUrlList.add(new ConfigUrl("http3"));
    return configUrlList;
  }
}