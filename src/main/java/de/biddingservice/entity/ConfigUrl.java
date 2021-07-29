package de.biddingservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
public class ConfigUrl {

  @Id
  @Setter
  @GeneratedValue
  private Long id;
  private final String url;

  public ConfigUrl() {
    this.url = "";
  }
}
