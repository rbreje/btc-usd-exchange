package dev.breje.exchangeservice.task;

import java.util.Objects;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.breje.exchangeservice.ObjectConverter;
import dev.breje.exchangeservice.dto.ExchangeRateDto;
import dev.breje.exchangeservice.entity.ExchangeRate;
import dev.breje.exchangeservice.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BtcUsdExchangeTask extends TimerTask {

	@Autowired
	private WebClient webClient;

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void run() {
		if (Objects.nonNull(webClient)) {
			Mono<String> monoObject = webClient.get().uri("/api/last_price/BTC/USD")
					.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).retrieve().bodyToMono(String.class);
			String rawJson = monoObject.block();
			ExchangeRateDto exchangeRateDto = null;
			try {
				exchangeRateDto = mapper.readValue(rawJson, ExchangeRateDto.class);
				ExchangeRate exchangeRate = ObjectConverter.fromExchangeRateDto(exchangeRateDto);
				exchangeRateRepository.save(exchangeRate);
				log.debug("Object saved to DB = {}", exchangeRateDto);
			} catch (JsonProcessingException e) {
				log.error("Error parsing json when loading data into DB!", e);
			}
		}
	}

}
