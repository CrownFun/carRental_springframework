package pl.filewicz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.filewicz.model.Car;
import pl.filewicz.model.Category;
import pl.filewicz.model.Client;
import pl.filewicz.repository.CarRepository;
import pl.filewicz.repository.CategoryRepository;
import pl.filewicz.repository.ClientRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRental2019SpringApplicationTests {

    @Autowired
    private
    ClientRepository clientRepository;

    @Autowired
    private
    CarRepository carRepository;

    @Autowired
    private
    CategoryRepository categoryRepository;

    @Test
    public void cretaeNewClient() {
        String lastName = "Adamiak";

        Client client = new Client();
        client.setName("Adam");
        client.setLastName(lastName);
        client.setAddres("Warszawa 99-999, Warszawska 1");
        client.setPhoneNumber(111222333);
        clientRepository.save(client);
        List<Client> clientFound = clientRepository.findBylastName(lastName);
        assertEquals(lastName, client.getLastName());

    }

    @Test
    public void createNewCarAndSave() {

        String brand = "Mercedes";
        Car car = new Car();
        car.setBrand(brand);
        car.setModel("Benz");
        car.setColor("Srebrny");
        car.setYear(2017);
        car.setFuelType("Diesel");
        car.setGearBox("Automat");
        car.setVinNumber("MD300");
        carRepository.save(car);
        List<Car> carFound = carRepository.findByBrand(brand);
        assertEquals(brand, carFound.get(0).getBrand());
    }

    @Test
    public void createNewCategoryAndSave() {

        String categoryName = "SUV";
        Category category = new Category();
        category.setName(categoryName);
        category.setDescription("Łączy cechy samochodu osobowego i terenowego, zapewniają wysoki komfort podróżnowania");
        categoryRepository.save(category);
        Optional<Category> categoryFound = categoryRepository.findByName(categoryName);
        assertEquals(categoryName, categoryFound.get().getName());

    }

    @Test
    @Transactional
    public void findClientAndCarInDatabaseAndRentCar() {
        String brand = "Renault";
        Car car = carRepository.findByBrand(brand).get(0);
        Client client = clientRepository.findBylastName("Kowal").get(0);

        client.addCars(car);

        Client clientFound = clientRepository.findBylastName("Kowal").get(0);

        assertEquals(brand, clientFound.getCars().get(0).getBrand());
    }

    @Test
    public void readCarfromDatabase() {
        Car car = carRepository.findByBrand("Renault").get(0);
        assertEquals("Renault", car.getBrand());

    }

    @Test
    public void removeClientFromDatabase() {

        Client client = clientRepository.findBylastName("Kowal").get(0);
        clientRepository.delete(client);
        assertEquals(0, clientRepository.findBylastName("Kowal").size());

    }


}
