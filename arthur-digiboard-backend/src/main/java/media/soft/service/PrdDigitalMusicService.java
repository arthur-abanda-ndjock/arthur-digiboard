package media.soft.service;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdDigitalMusic;
import media.soft.repository.PrdDigitalMusicRepositoryDao;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrdDigitalMusicService {

    private final PrdDigitalMusicRepositoryDao prdDigitalMusicRepositoryDao;

    public void insertPrdDigitalMusic(PrdDigitalMusic prdDigitalMusic) {
        prdDigitalMusicRepositoryDao.insert(prdDigitalMusic);
    }

    public void updatePrdDigitalMusicById(int trackId, PrdDigitalMusic prdDigitalMusic) {
        prdDigitalMusicRepositoryDao.updateById(trackId, prdDigitalMusic);
    }

    public void deletePrdDigitalMusicById(int trackId) {
        prdDigitalMusicRepositoryDao.deleteById(trackId);
    }
}

