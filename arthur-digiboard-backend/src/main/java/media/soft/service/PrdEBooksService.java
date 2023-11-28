package media.soft.service;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdEBook;
import media.soft.repository.PrdEBooksRepositoryDao;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrdEBooksService {

    private final PrdEBooksRepositoryDao prdEBooksRepositoryDao;

    public void insertPrdEBooks(PrdEBook prdEBook) {
        prdEBooksRepositoryDao.insert(prdEBook);
    }

    public void updatePrdEBooksById(int bookId, PrdEBook prdEBook) {
        prdEBooksRepositoryDao.updateById(bookId, prdEBook);
    }

    public void deletePrdEBooksById(int bookId) {
        prdEBooksRepositoryDao.deleteById(bookId);
    }
}
