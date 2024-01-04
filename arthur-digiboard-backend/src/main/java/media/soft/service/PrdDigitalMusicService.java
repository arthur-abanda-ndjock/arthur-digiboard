package media.soft.service;

import media.soft.model.PrdDigitalMusic;
import media.soft.repository.PrdDigitalMusicRepositoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrdDigitalMusicService {

    private final PrdDigitalMusicRepositoryDao prdDigitalMusicRepositoryDao;

    public  PrdDigitalMusicService(@Autowired PrdDigitalMusicRepositoryDao prdDigitalMusicRepositoryDao){
        this.prdDigitalMusicRepositoryDao = prdDigitalMusicRepositoryDao;
    }

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

