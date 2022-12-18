package my.dao;

import my.model.CarImage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ImageDaoImpl implements ImageDao {
    @PersistenceContext(name = "localContainerEntityManagerFactoryBean")
    private EntityManager entityManager;

    @Override
    public CarImage getById(Long id) {
        return entityManager.find(CarImage.class, id);
    }
}
