package dev.breje.exchangeservice;

import java.time.LocalDateTime;

import dev.breje.exchangeservice.dto.ExchangeRateDto;
import dev.breje.exchangeservice.entity.ExchangeRate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectConverter {

	public static ExchangeRate fromExchangeRateDto(ExchangeRateDto exchangeRateDto) {
		LocalDateTime ldt = LocalDateTime.now();
		return new ExchangeRate(exchangeRateDto.getLPrice(), ldt);
	}

}
