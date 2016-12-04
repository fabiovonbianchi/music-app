package au.com.sample.service;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import au.com.sample.lastfm.LastFMClient;
import au.com.sample.model.Artist;
import au.com.sample.model.Page;
import au.com.sample.model.Track;
import de.umass.lastfm.ImageSize;
import de.umass.lastfm.PaginatedResult;

@Service
public class MusicService {

	private final static Integer defaultPageSize = 5;
	
	private LastFMClient lastFMClient;

	public MusicService(LastFMClient lastFMClient) {
		this.lastFMClient = lastFMClient;
	}

	public Page<Artist> getTopArtistsByLocation(String country, Integer page) {
		checkArgument(!Strings.isNullOrEmpty(country), "country cannot be null or empty");

		PaginatedResult<de.umass.lastfm.Artist> artists = lastFMClient.getLastFMTopArtistsByCountry(country, page, defaultPageSize);
		if (artists == null || artists.getPageResults() == null) {
			return new Page<Artist>(0L, 0L, 0, Collections.emptyList());
		}
		List<Artist> result = artists.getPageResults().stream()
				.map(a -> new Artist().withId(a.getMbid()).withName(a.getName())
				.withImageURL(a.getImageURL(ImageSize.MEDIUM))).collect(toList());
		
		// total amount of items could be 
		long totalElements = artists.getTotalPages() * defaultPageSize;
		return new Page<Artist>(artists.getPage(), totalElements, defaultPageSize, result);

	}

	public List<Track> getTopTracks(String artistId) {
		checkArgument(!Strings.isNullOrEmpty(artistId), "artistId cannot be null or empty");

		Collection<de.umass.lastfm.Track> tracks = lastFMClient.getLastFMTopTracks(artistId);
		if (tracks == null) {
			return Collections.emptyList();
		}
		return tracks.stream().map(t -> new Track().withName(t.getName())).collect(toList());

	}

}
