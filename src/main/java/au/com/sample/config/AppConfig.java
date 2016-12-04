package au.com.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.umass.lastfm.Caller;
import de.umass.lastfm.cache.MemoryCache;

@Configuration
public class AppConfig {

	@Bean
	public Caller configureLastFMClient(){
		Caller client = Caller.getInstance();
		client.setUserAgent("tst");
		client.setCache(new MemoryCache());
		
		return client;
	}
}
