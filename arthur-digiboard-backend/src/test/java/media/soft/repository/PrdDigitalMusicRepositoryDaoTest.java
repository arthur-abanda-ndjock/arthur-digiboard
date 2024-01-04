package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import media.soft.model.PrdDigitalMusic;

@ExtendWith(MockitoExtension.class)
class PrdDigitalMusicRepositoryDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @InjectMocks
    private PrdDigitalMusicRepositoryDao prdDigitalMusicRepositoryDao;

    @Test
    void testInsert() {
        PrdDigitalMusic prdDigitalMusic = new PrdDigitalMusic();
        prdDigitalMusic.setTrackID(1);
        prdDigitalMusic.setTitle("SongTitle");
        prdDigitalMusic.setArtist("ArtistName");
        prdDigitalMusic.setAlbum("AlbumName");
        prdDigitalMusic.setGenre("Pop");
        prdDigitalMusic.setDuration(Time.valueOf("00:04:30"));
        prdDigitalMusic.setReleaseDate(java.sql.Date.valueOf("2023-01-01"));
        prdDigitalMusic.setPrice(9.99);

        prdDigitalMusicRepositoryDao.insert(prdDigitalMusic);

        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testUpdateById() {
        int trackId = 1;
        PrdDigitalMusic prdDigitalMusic = new PrdDigitalMusic();
        prdDigitalMusic.setTitle("UpdatedTitle");
        prdDigitalMusic.setArtist("UpdatedArtist");
        prdDigitalMusic.setAlbum("UpdatedAlbum");
        prdDigitalMusic.setGenre("UpdatedGenre");
        prdDigitalMusic.setDuration(Time.valueOf("00:05:00"));
        prdDigitalMusic.setReleaseDate(java.sql.Date.valueOf("2023-02-01"));
        prdDigitalMusic.setPrice(12.99);

        prdDigitalMusicRepositoryDao.updateById(trackId, prdDigitalMusic);

        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testDeleteById() {
        int trackId = 1;

        prdDigitalMusicRepositoryDao.deleteById(trackId);

        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testReadAll() throws SQLException {

        PrdDigitalMusic prdDigitalMusic = new PrdDigitalMusic();
        prdDigitalMusic.setTrackID(1);
        prdDigitalMusic.setTitle("UpdatedTitle");
        prdDigitalMusic.setArtist("UpdatedArtist");
        prdDigitalMusic.setAlbum("UpdatedAlbum");
        prdDigitalMusic.setGenre("UpdatedGenre");
        prdDigitalMusic.setDuration(Time.valueOf("00:05:00"));
        prdDigitalMusic.setReleaseDate(java.sql.Date.valueOf("2023-02-01"));
        prdDigitalMusic.setPrice(12.99);

        when(namedJdbcTemplate.query(anyString(), any(PrdDigitalMusicRepositoryDao.PrdDigitalMusicRowMapper.class)))
                .thenReturn(Arrays.asList(prdDigitalMusic, prdDigitalMusic));

        List<PrdDigitalMusic> actualPrdDigitalMusic = prdDigitalMusicRepositoryDao.readAll();

        verify(namedJdbcTemplate).query(anyString(), any(PrdDigitalMusicRepositoryDao.PrdDigitalMusicRowMapper.class));

        assertEquals(2,
                actualPrdDigitalMusic.size());
    }

    @Test
    void testPrdDigitalMusicRowMapper() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("TrackID")).thenReturn(1);
        when(resultSet.getString("Title")).thenReturn("SongTitle");
        when(resultSet.getString("Artist")).thenReturn("ArtistName");
        when(resultSet.getString("Album")).thenReturn("AlbumName");
        when(resultSet.getString("Genre")).thenReturn("Pop");
        when(resultSet.getTime("Duration")).thenReturn(Time.valueOf("00:04:30"));
        when(resultSet.getDate("ReleaseDate")).thenReturn(java.sql.Date.valueOf("2023-01-01"));
        when(resultSet.getDouble("Price")).thenReturn(9.99);

        PrdDigitalMusicRepositoryDao.PrdDigitalMusicRowMapper prdDigitalMusicRowMapper = new PrdDigitalMusicRepositoryDao.PrdDigitalMusicRowMapper();

        PrdDigitalMusic actualPrdDigitalMusic = prdDigitalMusicRowMapper.mapRow(resultSet, 1);

        assertEquals(1, actualPrdDigitalMusic.getTrackID());
        assertEquals("SongTitle", actualPrdDigitalMusic.getTitle());
        assertEquals("ArtistName", actualPrdDigitalMusic.getArtist());
        assertEquals("AlbumName", actualPrdDigitalMusic.getAlbum());
        assertEquals("Pop", actualPrdDigitalMusic.getGenre());
        assertEquals(Time.valueOf("00:04:30"), actualPrdDigitalMusic.getDuration());
        assertEquals(java.sql.Date.valueOf("2023-01-01"), actualPrdDigitalMusic.getReleaseDate());
        assertEquals(9.99D, actualPrdDigitalMusic.getPrice());

    }
}
