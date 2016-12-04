package au.com.sample.lastfm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.annotations.VisibleForTesting;

import au.com.sample.config.LastFMConfig;
import de.umass.lastfm.Caller;
import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.ResponseBuilder;
import de.umass.lastfm.Result;

@Component
public class LastFMClient {

	private final LastFMConfig config;

	public LastFMClient(LastFMConfig config) {
		this.config = config;
	}

	public PaginatedResult<de.umass.lastfm.Artist> getLastFMTopArtistsByCountry(String country, Integer page,
			Integer pageSize) {
		if (page == null) {
			page = 1;
		}
		Map<String, String> params = new HashMap<>();
		params.put("country", country);
		params.put("page", String.valueOf(page));

		if (pageSize != null) {
			params.put("limit", String.valueOf(pageSize));

		}
		Result result = getResult(getLastFMClient().call("geo.getTopArtists", config.getSecretKey(), params));

		return ResponseBuilder.buildPaginatedResult(result, de.umass.lastfm.Artist.class);
	}

	public Collection<de.umass.lastfm.Track> getLastFMTopTracks(String artistId) {
		Result result = getResult(
				getLastFMClient()
				.call("artist.getTopTracks", config.getSecretKey(), "mbid", artistId));
		
		return ResponseBuilder.buildCollection(result, de.umass.lastfm.Track.class);

	}
	
	private Result getResult(Result result) {
		if (!result.isSuccessful()) {
			throw new RuntimeException(result.getErrorMessage());
		}
		return result;
	}

	@VisibleForTesting
	protected Caller getLastFMClient() {
		return Caller.getInstance();
	}
}
