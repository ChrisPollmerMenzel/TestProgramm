package de.biddingservice.api;

import de.biddingservice.service.impl.BiddingServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test controller.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BiddingControllerTest {

  @LocalServerPort
  private int port;

  @MockBean
  private BiddingServiceImpl biddingService;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testSetBidders() {

    List<String> urlList = new ArrayList<>();
    urlList.add("http://localhost:8081");
    urlList.add("http://localhost:8082");
    urlList.add("http://localhost:8083");

    this.restTemplate
        .postForObject("http://localhost:" + port + "/bidders", urlList, String.class);

    verify(biddingService, times(1)).setBiddingConfig(any());
  }

  @Test
  void testBidForAuction() {

    Map<String, String> attributes = new HashMap<>();
    attributes.put("a", "5");

    when(biddingService.bidForAuction(any())).thenReturn("a");

    String response = this.restTemplate
        .postForObject("http://localhost:" + port + "/5", attributes, String.class);

    assertEquals("a", response);
  }
}