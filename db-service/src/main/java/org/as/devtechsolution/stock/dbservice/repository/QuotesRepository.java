package org.as.devtechsolution.stock.dbservice.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.as.devtechsolution.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {
	
	List<Quote> findByUserName(String username);

	@GetMapping("/{username}")
	default List<String> getQoutesByUserName(final String username){
		
		return findByUserName(username)
		.stream()
		.map(quote->{
			return quote.getQoute();
		})
		.collect(Collectors.toList())
		;
		
		
	}

}
