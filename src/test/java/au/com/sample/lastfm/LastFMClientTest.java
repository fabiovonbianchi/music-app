package au.com.sample.lastfm;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.com.sample.config.LastFMConfig;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;
import de.umass.lastfm.ResponseBuilder;
import de.umass.lastfm.Result;
import de.umass.lastfm.Track;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Caller.class, ResponseBuilder.class})
public class LastFMClientTest {

	@Test
	public void test_getLastFMTopArtistsByCountry() {
		PowerMockito.mockStatic(Caller.class);
		PowerMockito.mockStatic(ResponseBuilder.class);
		Caller caller = Mockito.mock(Caller.class);
		
		LastFMConfig config = Mockito.mock(LastFMConfig.class);
		when(config.getSecretKey()).thenReturn("sk");
		
		Result result = Mockito.mock(Result.class);
		when(result.isSuccessful()).thenReturn(true);
		
		LastFMClient client = spy(new LastFMClient(config));
		doReturn(caller).when(client).getLastFMClient();
		
		Map<String,String> params = new HashMap<>();
		params.put("limit" , "2");
		params.put("page" , "1");
		params.put("country" , "c");
		
		when(caller.call("geo.getTopArtists", "sk", params)).thenReturn(result);
		client.getLastFMTopArtistsByCountry("c", 1, 2);
		
		verify(caller).call("geo.getTopArtists", "sk", params);
		PowerMockito.verifyStatic();
		ResponseBuilder.buildPaginatedResult(result, Artist.class);
	}
	
	@Test(expected=RuntimeException.class)
	public void test_getLastFMTopArtistsByCountry_fail() {
		PowerMockito.mockStatic(Caller.class);
		Caller caller = Mockito.mock(Caller.class);
		
		LastFMConfig config = Mockito.mock(LastFMConfig.class);
		when(config.getSecretKey()).thenReturn("sk");
		
		Result result = Mockito.mock(Result.class);
		when(result.isSuccessful()).thenReturn(false);
		
		LastFMClient client = spy(new LastFMClient(config));
		doReturn(caller).when(client).getLastFMClient();
		
		Map<String,String> params = new HashMap<>();
		params.put("limit" , "2");
		params.put("page" , "1");
		params.put("country" , "c");
		
		when(caller.call("geo.getTopArtists", "sk", params)).thenReturn(result);
		client.getLastFMTopArtistsByCountry("c", 1, 2);
	}
	
	@Test
	public void test_getLastFMTopTracks() {
		PowerMockito.mockStatic(Caller.class);
		PowerMockito.mockStatic(ResponseBuilder.class);
		Caller caller = Mockito.mock(Caller.class);
		
		LastFMConfig config = Mockito.mock(LastFMConfig.class);
		when(config.getSecretKey()).thenReturn("sk");
		Result result = Mockito.mock(Result.class);
		when(result.isSuccessful()).thenReturn(true);
		
		LastFMClient client = spy(new LastFMClient(config));
		doReturn(caller).when(client).getLastFMClient();
		
		when(caller.call("artist.getTopTracks", "sk", "mbid","a")).thenReturn(result);
		client.getLastFMTopTracks("a");
		
		verify(caller).call("artist.getTopTracks", "sk", "mbid", "a");
		PowerMockito.verifyStatic();
		ResponseBuilder.buildCollection(result, Track.class);
	}
}
