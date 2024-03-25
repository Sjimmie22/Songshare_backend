package com.example.SongShareBackend;

import com.example.SongShareBackend.Repositories.SongRepo;
import com.example.SongShareBackend.models.SongshareSong;
import com.example.SongShareBackend.services.Songservice;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SongShareBackendApplicationTests {

	@InjectMocks
	private Songservice songservice;

	@Mock SongRepo songRepo;

	@Test
	void testGetAllSongs() {
		//assert
		SongshareSong song1 = new SongshareSong();
		SongshareSong song2 = new SongshareSong();

		List<SongshareSong> expectedSongs = Arrays.asList(song1, song2);
		when(songRepo.findAll()).thenReturn(expectedSongs);

		//act
		List<SongshareSong> result = songservice.getAllSongs();

		//assert
		assertEquals(expectedSongs, result);
	}

	@Test
	public void testGetSongById() {
		// Mock the behavior of songRepo.findById()
		Long songId = 1L;
		SongshareSong expectedSong = new SongshareSong(); // You may need to provide an actual song instance
		when(songRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(expectedSong));

		// Call the method you want to test
		Optional<SongshareSong> result = songservice.getSongById(songId);

		// Verify the result
		assertEquals(expectedSong, result.orElse(null));
	}

}
