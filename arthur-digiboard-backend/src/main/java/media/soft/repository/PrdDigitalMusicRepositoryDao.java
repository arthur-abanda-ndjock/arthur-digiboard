package media.soft.repository;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdDigitalMusic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PrdDigitalMusicRepositoryDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrdDigitalMusicRepositoryDao.class);

    
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PrdDigitalMusicRepositoryDao(@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insert(PrdDigitalMusic prdDigitalMusic) {
        String sql = "INSERT INTO PrdDigitalMusic (TrackID, Title, Artist, Album, Genre, Duration, ReleaseDate, Price) "
                +
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

    // Read All Method
    public List<PrdDigitalMusic> readAll() {
        String sql = "SELECT * FROM PrdDigitalMusic";
        return namedJdbcTemplate.query(sql, new PrdDigitalMusicRowMapper());
    }

    // RowMapper
    private static class PrdDigitalMusicRowMapper implements RowMapper<PrdDigitalMusic> {
        @Override
        public PrdDigitalMusic mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrdDigitalMusic prdDigitalMusic = new PrdDigitalMusic();
            prdDigitalMusic.setTrackID(rs.getInt("TrackID"));
            prdDigitalMusic.setTitle(rs.getString("Title"));
            prdDigitalMusic.setArtist(rs.getString("Artist"));
            prdDigitalMusic.setAlbum(rs.getString("Album"));
            prdDigitalMusic.setGenre(rs.getString("Genre"));
            prdDigitalMusic.setDuration(rs.getTime("Duration"));
            prdDigitalMusic.setReleaseDate(rs.getDate("ReleaseDate"));
            prdDigitalMusic.setPrice(rs.getDouble("Price"));
            return prdDigitalMusic;
        }
    }
}
