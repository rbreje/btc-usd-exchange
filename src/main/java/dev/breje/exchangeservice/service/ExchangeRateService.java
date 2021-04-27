package dev.breje.exchangeservice.service;

import java.util.Objects;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.breje.exchangeservice.task.BtcUsdExchangeTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExchangeRateService {

	@Value("${exchange.refresh.time}")
	private String refreshTime;

	@Autowired
	private BtcUsdExchangeTask scheduledTask;

	@PostConstruct
	public void atStartup() {
		startLoading();
	}

	public void startLoading() {
		int actualRefreshTime = 1000;
		if (Objects.nonNull(actualRefreshTime)) {
			actualRefreshTime = Integer.parseInt(refreshTime) * 1000;
		}
		Timer timer = new Timer();
		timer.schedule(scheduledTask, 0, actualRefreshTime);
		log.debug("The scheduled loading task was started successfully...");
	}
}
