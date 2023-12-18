package media.soft.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import media.soft.model.PrdEBook;
import media.soft.repository.PrdEBooksRepositoryDao;

@ExtendWith(MockitoExtension.class)
class PrdEBooksServiceTest {

    @Mock
    private PrdEBooksRepositoryDao prdEBooksRepositoryDao;

    @InjectMocks
    private PrdEBooksService prdEBooksService;

    @Test
    void testInsertPrdEBooks() {
        PrdEBook prdEBook = new PrdEBook();
        prdEBooksService.insertPrdEBooks(prdEBook);

        verify(prdEBooksRepositoryDao).insert(prdEBook);
    }

    @Test
    void testUpdatePrdEBooksById() {
        int bookId = 1;
        PrdEBook prdEBook = new PrdEBook();
        prdEBooksService.updatePrdEBooksById(bookId, prdEBook);

        verify(prdEBooksRepositoryDao).updateById(bookId, prdEBook);
    }

    @Test
    void testDeletePrdEBooksById() {
        int bookId = 1;
        prdEBooksService.deletePrdEBooksById(bookId);

        verify(prdEBooksRepositoryDao).deleteById(bookId);
    }
}
