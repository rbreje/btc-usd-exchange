package dev.breje.exchangeservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.breje.exchangeservice.entity.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

	@Query("from EXCHANGE_RATE_TBL order by LOAD_DATE desc")
	List<ExchangeRate> findLatest(Pageable limit);

	@Query("from EXCHANGE_RATE_TBL where LOAD_DATE > ?1 AND LOAD_DATE < ?2")
	List<ExchangeRate> findRanged(LocalDateTime startDate, LocalDateTime endDate);

}
