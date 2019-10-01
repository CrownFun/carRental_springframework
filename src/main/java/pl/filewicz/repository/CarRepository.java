package pl.filewicz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.filewicz.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository  extends JpaRepository<Car,Long> {

    Optional<Car> findByVinNumber(String vin);

    List<Car> findByBrand(String brand);
    List<Car> findAllByYear(int year);
    List<Car> findAllByColor(String color);
    List<Car> findAllByCategory(String category);
}
