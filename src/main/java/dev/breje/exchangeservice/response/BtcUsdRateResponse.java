package dev.breje.exchangeservice.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BtcUsdRateResponse extends DefaultResponse {

	private String price;

	private String date;

	public BtcUsdRateResponse(String price, String date) {
		super("SUCCESS");
		this.price = price;
		this.date = date;
	}

}
