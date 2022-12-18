package my.dao;

import my.model.Car;
import my.model.CarModel;
import org.hibernate.graph.GraphSemantic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @PersistenceContext(name = "localContainerEntityManagerFactoryBean")
    private EntityManager entityManager;

    @Override
    public Car saveOrUpdate(Car car) {
        entityManager.persist(car);
        return car;
    }

    @Override
    public void delete(Car car) {
        entityManager.remove(car);
    }

    @Override
    public List<Car> findAll() {
        EntityGraph<Car> entityGraph = entityManager.createEntityGraph(Car.class);
        entityGraph.addAttributeNodes("carModel");
        Subgraph<CarModel> entitySubGraph = entityGraph.addSubgraph("carModel", CarModel.class);
        entitySubGraph.addAttributeNodes("image");
        return entityManager.createQuery("select c from Car c", Car.class)
                .setHint(GraphSemantic.LOAD.getJpaHintName(), entityGraph)
                .getResultList();
    }

    @Override
    public long getCarCount() {
        return (long) entityManager.createQuery("select count(c) from Car c").getSingleResult();
    }
}
