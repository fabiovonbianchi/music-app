package au.com.sample.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import au.com.sample.lastfm.LastFMClient;
import au.com.sample.model.Artist;
import au.com.sample.model.Page;
import au.com.sample.model.Track;
import de.umass.lastfm.ImageSize;
import de.umass.lastfm.PaginatedResult;

public class MusicServiceTest {

	@SuppressWarnings("unchecked")
	@Test
	public void test_getTopArtistsByLocation() {
		LastFMClient client = Mockito.mock(LastFMClient.class);

		de.umass.lastfm.Artist artist1 = Mockito.mock(de.umass.lastfm.Artist.class);
		when(artist1.getMbid()).thenReturn("1");
		when(artist1.getName()).thenReturn("n1");
		when(artist1.getImageURL(ImageSize.MEDIUM)).thenReturn("img1");

		de.umass.lastfm.Artist artist2 = Mockito.mock(de.umass.lastfm.Artist.class);
		when(artist2.getMbid()).thenReturn("2");
		when(artist2.getName()).thenReturn("n2");
		when(artist2.getImageURL(ImageSize.MEDIUM)).thenReturn("img2");

		PaginatedResult<de.umass.lastfm.Artist> artists = Mockito.mock(PaginatedResult.class);
		when(artists.getPage()).thenReturn(1);
		when(artists.getPage()).thenReturn(10);
		when(artists.getPageResults()).thenReturn(Arrays.asList(artist1, artist2));

		when(client.getLastFMTopArtistsByCountry("c", 1, 5)).thenReturn(artists);

		MusicService service = new MusicService(client);

		Page<Artist> result = service.getTopArtistsByLocation("c", 1);

		assertEquals(2, result.getElements().size());
		Artist expected1 = result.getElements().get(0);
		assertEquals("1", expected1.getId());
		assertEquals("n1", expected1.getName());
		assertEquals("img1", expected1.getImageURL());

		Artist expected2 = result.getElements().get(1);
		assertEquals("2", expected2.getId());
		assertEquals("n2", expected2.getName());
		assertEquals("img2", expected2.getImageURL());

		verify(client).getLastFMTopArtistsByCountry("c", 1, 5);

	}

	@Test(expected = IllegalArgumentException.class)
	public void test_getTopArtistsByLocation_fail() {
		MusicService service = new MusicService(Mockito.mock(LastFMClient.class));
		service.getTopArtistsByLocation(null, 1);

	}

	@Test
	public void test_getTopTracks() {
		LastFMClient client = Mockito.mock(LastFMClient.class);

		de.umass.lastfm.Track track1 = Mockito.mock(de.umass.lastfm.Track.class);
		when(track1.getName()).thenReturn("t1");

		de.umass.lastfm.Track track2 = Mockito.mock(de.umass.lastfm.Track.class);
		when(track2.getName()).thenReturn("t2");

		Collection<de.umass.lastfm.Track> tracks = Arrays.asList(track1, track2);
		when(client.getLastFMTopTracks("artistid")).thenReturn(tracks);

		MusicService service = new MusicService(client);

		List<Track> result = service.getTopTracks("artistid");

		assertEquals(2, result.size());
		Track expected1 = result.get(0);
		assertEquals("t1", expected1.getName());

		Track expected2 = result.get(1);
		assertEquals("t2", expected2.getName());
		verify(client).getLastFMTopTracks("artistid");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_getTopTracks_fail() {
		LastFMClient client = Mockito.mock(LastFMClient.class);

		MusicService service = new MusicService(client);
		service.getTopTracks(null);
	}
}
