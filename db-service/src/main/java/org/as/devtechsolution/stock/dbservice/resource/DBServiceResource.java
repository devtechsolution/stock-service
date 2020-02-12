package org.as.devtechsolution.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.as.devtechsolution.stock.dbservice.model.Quote;
import org.as.devtechsolution.stock.dbservice.model.Quotes;
import org.as.devtechsolution.stock.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {
	
	@Autowired
	private QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQoutes(@PathVariable("username") final String username){
		
		return getQuoteByUserName(username);
		
		
	}

	private List<String> getQuoteByUserName(final String username) {
		return quotesRepository.findByUserName(username)
		.stream()
		.map(quote->{
			return quote.getQoute();
		})
		.collect(Collectors.toList());
	}
	
	@PostMapping("/{add}")
	public List<String> add(@RequestBody final Quotes quotes){
		
		/*
		 * quotes.getQuotes() .stream() .forEach(quote->{quotesRepository.save(new
		 * Quote(quotes.getUserName(), quote)); });
		 */
		quotes.getQuotes()
		.stream()
		.map(quote-> new Quote(quotes.getUserName(), quote))
		.forEach(quote->quotesRepository.save(quote));
		
		return getQuoteByUserName(quotes.getUserName());
	}
	
	@DeleteMapping("/{username}")
	public List<String> delete(@PathVariable("username") final String username) {
		List<Quote>  quotes= quotesRepository.findByUserName(username);
		quotesRepository.deleteAll(quotes);
		
		return getQuoteByUserName(username);
		
	}

	
}
