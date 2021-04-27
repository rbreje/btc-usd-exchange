package dev.breje.exchangeservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "EXCHANGE_RATE_TBL")
@Data
@NoArgsConstructor
public class ExchangeRate implements Serializable {

	private static final long serialVersionUID = 4180436062132342217L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "PRICE")
	private String price;

	@Column(name = "LOAD_DATE")
	private LocalDateTime date;

	public ExchangeRate(String price, LocalDateTime date) {
		this.price = price;
		this.date = date;
	}

}
