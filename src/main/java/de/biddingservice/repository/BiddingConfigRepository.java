package de.biddingservice.repository;

import de.biddingservice.entity.ConfigUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiddingConfigRepository extends JpaRepository<ConfigUrl, Long> {

}
