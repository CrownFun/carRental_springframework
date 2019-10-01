package pl.filewicz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.searching.CarSearching;
import pl.filewicz.model.Car;
import pl.filewicz.model.Category;
import pl.filewicz.model.FuelType;
import pl.filewicz.model.GearBox;
import pl.filewicz.repository.CarRepository;
import pl.filewicz.repository.CategoryRepository;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CarController {


    private Scanner scanner;
    private CarRepository carRepository;
    private CategoryController categoryController;
    private CategoryRepository categoryRepository;
    private CarSearching carSearching;

    @Autowired
    public CarController(Scanner scanner, CarRepository carRepository, CategoryController categoryController, CategoryRepository categoryRepository, CarSearching carSearching) {
        this.scanner = scanner;
        this.carRepository = carRepository;
        this.categoryController = categoryController;
        this.categoryRepository = categoryRepository;
        this.carSearching = carSearching;
    }


    @Transactional
    public void createCar() {

        Car car = new Car();
        try {

            System.out.println("Podaj markę samochodu");
            car.setBrand(scanner.nextLine().toUpperCase());
            System.out.println("Podaj model samochodu");
            car.setModel(scanner.nextLine().toUpperCase());
            System.out.println("Podaj rok produkcji samochodu");
            car.setYear(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Podaj rodzaj  paliwa");
            printFuelTypes();
            car.setFuelType(scanner.nextLine().toUpperCase());
            System.out.println("Podaj kolor samochodu");
            car.setColor(scanner.nextLine().toUpperCase());
            System.out.println("Podaj rodzaj skrzyni biegów ");
            printGearBoxex();
            car.setGearBox(scanner.nextLine().toUpperCase());
            System.out.println("Podaj numer VIN samochodu");
            car.setVinNumber(scanner.nextLine().toUpperCase());
            System.out.println("1 - Podaj nazwę kategorii \n2 - Stwórz nową kategorię");

            car.setCategory(getCategory());

            carRepository.save(car);

        } catch (IllegalArgumentException e) {
            System.err.println("Nie udało się dodać nowego samochodu!");
        }
    }


    public void readCar() {
        carSearching.chooseSearchCriteria();
    }


    public void deleteCar() {
        Optional<Car> car = carSearching.searchCarByVinNumber();
        car.ifPresentOrElse(car1 -> {
            carRepository.delete(car1);
            System.out.println("Pomyślnie usunięto");
        }, () -> System.err.println("Nie udało się usunąć samochodu!"));

    }


    private void printGearBoxex() {
        for (GearBox gearBox : GearBox.values()) {
            System.out.println(gearBox);
        }
    }

    private void printFuelTypes() {
        for (FuelType type : FuelType.values()) {
            System.out.println(type);
        }
    }

    private Category getCategory() {
        int categoryOption = scanner.nextInt();
        scanner.nextLine();
        Category category = null;
        if (categoryOption == 1) {
            System.out.println("Podaj nazwę kategorii");
            category = categoryRepository.findByName(scanner.nextLine()).get();

        } else if (categoryOption == 2) {
            category = categoryController.createCategory();
        }
        return category;
    }


}
