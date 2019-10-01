package pl.filewicz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.CarController;
import pl.filewicz.controllers.CategoryController;
import pl.filewicz.controllers.ClientController;
import pl.filewicz.controllers.RentController;
import java.util.Arrays;
import java.util.Scanner;

@Service
public class ApplicationController {


    private Scanner scanner;
    private ClientController clientController;
    private CategoryController categoryController;
    private CarController carController;
    private RentController rentController;


    @Autowired
    public ApplicationController(Scanner scanner, ClientController clientController, CategoryController categoryController, CarController carController, RentController rentController) {
        this.scanner = scanner;
        this.clientController = clientController;
        this.categoryController = categoryController;
        this.carController = carController;
        this.rentController = rentController;
    }

    private void printOptions() {
        Arrays.stream(Options.values()).forEach(System.out::println);
    }

    public void mainLoop() {

        boolean isClose = false;

        while (!isClose) {
            System.out.println("Wybierz opcję");
            printOptions();
            Options option = chooseOption();
            if (option.equals(Options.EXIT)) {
                isClose = true;
            }
            executeOption(option);

        }
    }

    private Options chooseOption() {

        int optionValue = 0;


        boolean isProperOptonChoosen = false;

        while (!isProperOptonChoosen) {
            try {
                optionValue = scanner.nextInt();
            } catch (Exception e) {
                optionValue = 11;
            }

            if (optionValue < 1 || optionValue > 11) {
                System.err.println("Podaj numer operacji od 1 do 11 !!");
                printOptions();
            } else {
                isProperOptonChoosen = true;
            }
        }
        scanner.nextLine();
        scanner.close();
        return Options.values()[optionValue - 1];


    }

    private void executeOption(Options option) {

        switch (option) {
            case ADD_CAR: {
                carController.createCar();
                break;
            }
            case ADD_CATEGORY: {
                categoryController.createCategory();
                break;
            }
            case ADD_CLIENT: {
                clientController.createNewClient();
                break;
            }
            case RENT: {
                rentController.rent();
                break;
            }
            case REMOVE_CLIENT: {
                clientController.deleteClient();
                break;
            }
            case REMOVE_CATEGORY: {
                categoryController.deleteCategory();
                break;
            }
            case REMOVE_CAR: {
                carController.deleteCar();
                break;
            }
            case FIND_CLIENT: {
                clientController.readClient();
                break;
            }
            case FIND_CATEGORY: {
                categoryController.readCategory();
                break;
            }
            case FIND_CAR: {
                carController.readCar();
                break;
            }
            case EXIT: {
                System.out.println("Koniec programu");
                scanner.close();
                break;
            }
            default: {
                System.out.println("Nie poprawna cyfra!");
            }
        }

    }


}
