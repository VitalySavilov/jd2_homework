package my;

import lombok.RequiredArgsConstructor;
import my.dao.ImageDao;
import my.model.CarImage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final ImageDao imageDao;

    public CarImage getImageById(long imageId) {
        return imageDao.getById(imageId);
    }
}
