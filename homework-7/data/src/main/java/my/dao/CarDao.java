package my.dao;

import my.model.Car;

import java.util.List;

public interface CarDao {
    Car saveOrUpdate(Car car);

    void delete(Car car);

    List<Car> findAll();

    long getCarCount();
}
