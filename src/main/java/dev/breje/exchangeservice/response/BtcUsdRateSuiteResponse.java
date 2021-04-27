package dev.breje.exchangeservice.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BtcUsdRateSuiteResponse extends DefaultResponse {

	private List<BtcUsdRateResponse> rates;

	public BtcUsdRateSuiteResponse(List<BtcUsdRateResponse> rates) {
		super("SUCCESS");
		this.rates = rates;
	}

	public BtcUsdRateSuiteResponse(String message) {
		super(message);
		this.rates = new ArrayList<>();
	}

}
