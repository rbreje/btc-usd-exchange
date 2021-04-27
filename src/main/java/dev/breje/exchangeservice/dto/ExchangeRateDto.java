package dev.breje.exchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {

	@JsonProperty("lprice")
	private String lPrice;

	@JsonProperty("curr1")
	private String curr1;

	@JsonProperty("curr2")
	private String curr2;

	@Override
	public String toString() {
		return "ExchangeRate [price=" + lPrice + ", currency1=" + curr1 + ", currency2=" + curr2 + "]";
	}

}
