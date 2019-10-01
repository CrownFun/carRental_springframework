package pl.filewicz.controllers.searching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.printing.PrintText;
import pl.filewicz.model.Car;
import pl.filewicz.model.Category;
import pl.filewicz.repository.CarRepository;
import pl.filewicz.repository.CategoryRepository;

import java.util.*;

@Service
public class CarSearching implements SearchCriteria {

    private CarRepository carRepository;
    private CategoryRepository categoryRepository;
    private Scanner scanner;
    private PrintText printText;

    @Autowired
    public CarSearching(CarRepository carRepository, CategoryRepository categoryRepository, Scanner scanner, PrintText printText) {
        this.carRepository = carRepository;
        this.categoryRepository = categoryRepository;
        this.scanner = scanner;
        this.printText = printText;
    }

    @Override
    public void chooseSearchCriteria() {
        System.out.println("Podaj parametry wyszukiwania");
        Arrays.stream(CarSearchCriteria.values()).forEach(System.out::println);
        CarSearchCriteria searchType = CarSearchCriteria.values()[scanner.nextInt() - 1];
        scanner.nextLine();

        switch (searchType) {
            case FIND_BY_BRAND: {
                printText.printListOfCars(searchCarByBrand());
                break;
            }
            case FIND_BY_YEAR: {
                printText.printListOfCars(searchCarByYear());
                break;
            }
            case FIND_BY_COLOR: {
                printText.printListOfCars(searchCarByColor());
                break;
            }
            case FIND_BY_CATEGORY: {
                printText.printListOfCars(searchCarByCategory());
                break;
            }
            case FIND_BY_VIN: {
                printText.printCar(searchCarByVinNumber());
            }
        }

    }

    private List<Car> searchCarByBrand() {
        System.out.println("Podaj markę samochodu");
        return carRepository.findByBrand(scanner.nextLine().toUpperCase());
    }

    public Optional<Car> searchCarByVinNumber() {
        System.out.println("Podaj numer VIN samochodu");
        return carRepository.findByVinNumber(scanner.nextLine().toUpperCase());
    }


    private List<Car> searchCarByYear() {
        System.out.println("Podaj rok produkcji samochodu");
        List<Car> cars = carRepository.findAllByYear(scanner.nextInt());
        scanner.nextLine();
        return cars;
    }

    private List<Car> searchCarByColor() {
        System.out.println("Podaj kolor samochodu");
        return carRepository.findAllByColor(scanner.nextLine().toUpperCase());

    }

    private List<Car> searchCarByCategory() {
        System.out.println("Podaj kategorię samochodu");
        List<Car> carList = new ArrayList<>();

        Optional<Category> category = categoryRepository.findByName(scanner.nextLine().toUpperCase());
        category.ifPresent(category1 -> {
            carList.addAll(category1.getCars());
        });

        return carList;
    }


}
