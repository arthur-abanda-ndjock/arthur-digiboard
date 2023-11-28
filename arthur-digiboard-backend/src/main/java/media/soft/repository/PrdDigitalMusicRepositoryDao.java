package media.soft.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PrdDigitalMusicRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public void insert(PrdDigitalMusic prdDigitalMusic) {
        String sql = "INSERT INTO PrdDigitalMusic (TrackID, Title, Artist, Album, Genre, Duration, ReleaseDate, Price) " +
                     "VALUES (:trackId, :title, :artist, :album, :genre, :duration, :releaseDate, :price)";
        
        Map<String, Object> params = new HashMap<>();
        params.put("trackId", prdDigitalMusic.getTrackID());
        params.put("title", prdDigitalMusic.getTitle());
        params.put("artist", prdDigitalMusic.getArtist());
        params.put("album", prdDigitalMusic.getAlbum());
        params.put("genre", prdDigitalMusic.getGenre());
        params.put("duration", prdDigitalMusic.getDuration());
        params.put("releaseDate", prdDigitalMusic.getReleaseDate());
        params.put("price", prdDigitalMusic.getPrice());

        namedJdbcTemplate.update(sql, params);
    }

    public void updateById(int trackId, PrdDigitalMusic prdDigitalMusic) {
        String sql = "UPDATE PrdDigitalMusic " +
                     "SET Title = :title, Artist = :artist, Album = :album, Genre = :genre, " +
                     "Duration = :duration, ReleaseDate = :releaseDate, Price = :price " +
                     "WHERE TrackID = :trackId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("trackId", trackId);
        params.put("title", prdDigitalMusic.getTitle());
        params.put("artist", prdDigitalMusic.getArtist());
        params.put("album", prdDigitalMusic.getAlbum());
        params.put("genre", prdDigitalMusic.getGenre());
        params.put("duration", prdDigitalMusic.getDuration());
        params.put("releaseDate", prdDigitalMusic.getReleaseDate());
        params.put("price", prdDigitalMusic.getPrice());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int trackId) {
        String sql = "DELETE FROM PrdDigitalMusic WHERE TrackID = :trackId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("trackId", trackId);

        namedJdbcTemplate.update(sql, params);
    }
}
