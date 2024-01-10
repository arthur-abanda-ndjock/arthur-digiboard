package media.soft.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import media.soft.model.PrdEBook;

@Repository
public class PrdEBooksRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PrdEBooksRepositoryDao(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public void insert(PrdEBook prdEBooks) {
        String sql = "INSERT INTO PrdEBooks (BookID, Title, Author, Genre, ISBN, Price, PublicationDate) " +
                "VALUES (:bookId, :title, :author, :genre, :isbn, :price, :publicationDate)";

        Map<String, Object> params = new HashMap<>();
        params.put("bookId", prdEBooks.getBookID());
        params.put("title", prdEBooks.getTitle());
        params.put("author", prdEBooks.getAuthor());
        params.put("genre", prdEBooks.getGenre());
        params.put("isbn", prdEBooks.getISBN());
        params.put("price", prdEBooks.getPrice());
        params.put("publicationDate", prdEBooks.getPublicationDate());

        namedJdbcTemplate.update(sql, params);
    }

    public void updateById(int bookId, PrdEBook prdEBooks) {
        String sql = "UPDATE PrdEBooks " +
                "SET Title = :title, Author = :author, Genre = :genre, " +
                "ISBN = :isbn, Price = :price, PublicationDate = :publicationDate " +
                "WHERE BookID = :bookId";

        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("title", prdEBooks.getTitle());
        params.put("author", prdEBooks.getAuthor());
        params.put("genre", prdEBooks.getGenre());
        params.put("isbn", prdEBooks.getISBN());
        params.put("price", prdEBooks.getPrice());
        params.put("publicationDate", prdEBooks.getPublicationDate());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int bookId) {
        String sql = "DELETE FROM PrdEBooks WHERE BookID = :bookId";

        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);

        namedJdbcTemplate.update(sql, params);
    }

    public List<PrdEBook> readAll() {
        String sql = "SELECT * FROM PrdEBooks";
        return namedJdbcTemplate.query(sql, new PrdEBookRowMapper());
    }

    private static class PrdEBookRowMapper implements RowMapper<PrdEBook> {
        @Override
        public PrdEBook mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrdEBook prdEBook = new PrdEBook();
            prdEBook.setBookID(rs.getInt("BookID"));
            prdEBook.setTitle(rs.getString("Title"));
            prdEBook.setAuthor(rs.getString("Author"));
            prdEBook.setGenre(rs.getString("Genre"));
            prdEBook.setISBN(rs.getString("ISBN"));
            prdEBook.setPrice(rs.getDouble("Price"));
            prdEBook.setPublicationDate(rs.getDate("PublicationDate"));
            return prdEBook;
        }
    }
}
