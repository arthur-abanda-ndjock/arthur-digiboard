package media.soft.repository;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdEBook;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PrdEBooksRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

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
}
