package au.com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.sample.model.Artist;
import au.com.sample.model.Page;
import au.com.sample.model.Track;
import au.com.sample.service.MusicService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

	private MusicService musicService;
	
	@Autowired
	public ArtistController(MusicService musicService) {
		this.musicService = musicService;
	}
	
	@RequestMapping("/top/{country}")
    Page<Artist> findPopular(@PathVariable("country") String country, @RequestParam(required = false ) Integer page ) {
        return musicService.getTopArtistsByLocation(country, page);
    }
	
	@RequestMapping("/{artistId}/top-tracks")
    List<Track> getTopTracks(@PathVariable("artistId") String artistId) {
        return musicService.getTopTracks(artistId);
    }
}
