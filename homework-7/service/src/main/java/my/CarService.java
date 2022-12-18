package my;

import lombok.RequiredArgsConstructor;
import my.dao.CarDao;
import my.model.Car;
import my.model.CarImage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {
    private final CarDao carDao;
    public void addCar(Car car, byte[] image) {
        if(car.getCarModel().getImage() == null) {
            CarImage carImage = new CarImage();
            carImage.setImage(image);
            car.getCarModel().setImage(carImage);
        }
        carDao.saveOrUpdate(car);
    }

    public List<Car> getAll() {
        return carDao.findAll();
    }

    public long getCarCount() {
        return carDao.getCarCount();
    }
}
