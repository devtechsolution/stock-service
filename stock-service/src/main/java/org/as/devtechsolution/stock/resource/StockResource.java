package org.as.devtechsolution.stock.resource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/{username}")
	public List<Stock> getStock(@PathVariable("username") String userName){
		
		ResponseEntity<List<String>> quoteResponse=
				restTemplate.exchange("http://localhost:8100/rest/db/"+ userName, HttpMethod.GET,
						null, new ParameterizedTypeReference<List<String>>() {
						});
		
		List<String> quotes = quoteResponse.getBody();
		/*
		 * quotes .stream() .map(quote->{ return getStockPrice(quote); })
		 * .collect(Collectors.toList());
		 * 
		 */
		
		
		return quotes
				.stream()
				.map(this::getStockPrice)
				.collect(Collectors.toList());
	}

	public Stock getStockPrice(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Stock(quote);
		}
	}
	
	

}
