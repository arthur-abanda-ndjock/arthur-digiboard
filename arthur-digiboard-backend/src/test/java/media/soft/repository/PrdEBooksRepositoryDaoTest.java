package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import media.soft.model.PrdEBook;

@ExtendWith(MockitoExtension.class)
class PrdEBooksRepositoryDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @InjectMocks
    private PrdEBooksRepositoryDao prdEBooksRepositoryDao;

    @Test
    void testInsert() {
        PrdEBook prdEBook = createSamplePrdEBook();

        prdEBooksRepositoryDao.insert(prdEBook);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testUpdateById() {
        int bookId = 1;
        PrdEBook prdEBook = createSamplePrdEBook();

        prdEBooksRepositoryDao.updateById(bookId, prdEBook);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testDeleteById() {
        int bookId = 1;

        prdEBooksRepositoryDao.deleteById(bookId);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testReadAll() {
        when(namedJdbcTemplate.query(anyString(), ArgumentMatchers.<RowMapper<PrdEBook>>any()))
                .thenReturn(Collections.singletonList(createSamplePrdEBook()));

        List<PrdEBook> actualPrdEBooks = prdEBooksRepositoryDao.readAll();

        verify(namedJdbcTemplate).query(anyString(), ArgumentMatchers.<RowMapper<PrdEBook>>any());

        List<PrdEBook> expectedPrdEBooks = Collections.singletonList(createSamplePrdEBook());
        assertEquals(expectedPrdEBooks, actualPrdEBooks);
    }

    @Test
    void testPrdEBookRowMapper() {

        when(namedJdbcTemplate.query(anyString(), ArgumentMatchers.<RowMapper<PrdEBook>>any()))
                .thenReturn(Collections.singletonList(createSamplePrdEBook()));

        List<PrdEBook> actualPrdEBooks = prdEBooksRepositoryDao.readAll();

        verify(namedJdbcTemplate).query(anyString(), ArgumentMatchers.<RowMapper<PrdEBook>>any());

        List<PrdEBook> expectedPrdEBooks = Collections.singletonList(createSamplePrdEBook());
        assertEquals(expectedPrdEBooks, actualPrdEBooks);
    }

    private PrdEBook createSamplePrdEBook() {
        PrdEBook prdEBook = new PrdEBook();
        prdEBook.setBookID(1);
        prdEBook.setTitle("Sample Title");
        prdEBook.setAuthor("Sample Author");
        prdEBook.setGenre("Sample Genre");
        prdEBook.setISBN("Sample ISBN");
        prdEBook.setPrice(19.99);
        prdEBook.setPublicationDate(Date.valueOf("2023-01-01"));
        return prdEBook;
    }
}
