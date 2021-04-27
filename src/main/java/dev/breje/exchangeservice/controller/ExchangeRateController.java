package dev.breje.exchangeservice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.breje.exchangeservice.entity.ExchangeRate;
import dev.breje.exchangeservice.repository.ExchangeRateRepository;
import dev.breje.exchangeservice.response.BtcUsdRateResponse;
import dev.breje.exchangeservice.response.BtcUsdRateSuiteResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/btc-usd")
public class ExchangeRateController {

	@Value("${exchange.date.format}")
	private String dateFormat;

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;

	@GetMapping("/latest")
	public BtcUsdRateResponse getLatestRate() {
		List<ExchangeRate> exchangeRates = exchangeRateRepository.findLatest(PageRequest.of(0, 1));
		ExchangeRate exchangeRate = exchangeRates.get(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String stringDate = exchangeRate.getDate().format(formatter);
		return new BtcUsdRateResponse(exchangeRate.getPrice(), stringDate);
	}

	@GetMapping("/range/{start}/{end}")
	public BtcUsdRateSuiteResponse getRangedRate(@PathVariable(value = "start") String startDate,
			@PathVariable(value = "end") String endDate) {
		log.debug("ENTER with start={}, end={}", startDate, endDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		try {
			LocalDateTime start = LocalDateTime.parse(startDate, formatter);
			LocalDateTime end = LocalDateTime.parse(endDate, formatter);
			List<ExchangeRate> exchangeRates = exchangeRateRepository.findRanged(start, end);
			List<BtcUsdRateResponse> individualResponses = exchangeRates.stream().map(rate -> {
				String formattedDate = rate.getDate().format(formatter);
				return new BtcUsdRateResponse(rate.getPrice(), formattedDate);
			}).collect(Collectors.toList());
			return new BtcUsdRateSuiteResponse(individualResponses);
		} catch (DateTimeParseException e) {
			log.error("The date instances are invalid formatted!");
			return new BtcUsdRateSuiteResponse(
					"ERROR: Invalid date provided. Please use the following format: " + dateFormat);
		}
	}
}
