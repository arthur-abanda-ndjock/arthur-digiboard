package media.soft.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import media.soft.model.PrdDigitalMusic;
import media.soft.repository.PrdDigitalMusicRepositoryDao;

@ExtendWith(MockitoExtension.class)
class PrdDigitalMusicServiceTest {

    @Mock
    private PrdDigitalMusicRepositoryDao prdDigitalMusicRepositoryDao;

    @InjectMocks
    private PrdDigitalMusicService prdDigitalMusicService;

    @Test
    void testInsertPrdDigitalMusic() {
        PrdDigitalMusic prdDigitalMusic = new PrdDigitalMusic();
        prdDigitalMusicService.insertPrdDigitalMusic(prdDigitalMusic);

        verify(prdDigitalMusicRepositoryDao).insert(prdDigitalMusic);
    }

    @Test
    void testUpdatePrdDigitalMusicById() {
        int trackId = 1;
        PrdDigitalMusic prdDigitalMusic = new PrdDigitalMusic();
        prdDigitalMusicService.updatePrdDigitalMusicById(trackId, prdDigitalMusic);

        verify(prdDigitalMusicRepositoryDao).updateById(trackId, prdDigitalMusic);
    }

    @Test
    void testDeletePrdDigitalMusicById() {
        int trackId = 1;
        prdDigitalMusicService.deletePrdDigitalMusicById(trackId);

        verify(prdDigitalMusicRepositoryDao).deleteById(trackId);
    }
}
