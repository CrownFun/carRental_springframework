package pl.filewicz.controllers.printing;

import org.springframework.stereotype.Service;
import pl.filewicz.model.Car;
import pl.filewicz.model.Category;
import pl.filewicz.model.Client;


import java.util.List;
import java.util.Optional;

@Service
public class PrintText {


    public void printCar(Optional<Car> car) {

        car.ifPresentOrElse(car1 -> {
            System.out.println("Marka: "+car1.getBrand() + "\nModel: " + car1.getModel() + "\nRok produkcji: " + car1.getYear() + "\nKolor: " + car1.getColor() + "\nRodzaj paliwa: " + car1.getFuelType() +
                    "\nSkrzynia biegów: " + car1.getGearBox() + "\nNumer VIN: " + car1.getVinNumber()+ "\nKategoria: "+car1.getCategory().getName());
            System.out.println("--------------------------------------------");
        }, () -> {
            System.out.println("Nie znaleziono auta o podanych kryteriach!");
        });
    }

    public void printListOfCars(List<Car> cars) {

        if (!cars.isEmpty()) {
            for (Car car1 : cars) {
                System.out.println("Marka: "+car1.getBrand() + "\nModel: " + car1.getModel() + "\nRok produkcji: " + car1.getYear() + "\nKolor: " + car1.getColor() + "\nRodzaj paliwa: " + car1.getFuelType() +
                        "\nSkrzynia biegów: " + car1.getGearBox() + "\nNumer VIN: " + car1.getVinNumber()+ "\nKategoria: "+car1.getCategory().getName());
                System.out.println("--------------------------------------------");
            }
        } else {
    System.err.println("Nie znaleziono pojazdów o podanych kryteriach!");
        }

    }


    public void printClient(Optional<Client> client) {

        client.ifPresentOrElse(client1 -> {
            System.out.println("Imie: "+client1.getName() + "\nNazwisko: " + client1.getLastName() + "\nAdres: " + client1.getAddres() + "\nNumer telefonu: " + client1.getPhoneNumber()+"lista wypożyczonych samochodów "+client1.getCars().size());
            System.out.println("--------------------------------------------");
        }, () -> {
            System.err.println("Nie znaleziono klienta o podanych kryteriach!");
        });
    }


    public void printListOfClients(List<Client> clients) {


        if (clients.size() > 0) {
            for (Client client1 : clients) {
                System.out.println("Imie: "+client1.getName() + "\nNazwisko: " + client1.getLastName() + "\nAdres: " + client1.getAddres() + "\nNumer telefonu: " + client1.getPhoneNumber());
                System.out.println("--------------------------------------------");
            }
        } else {
            System.out.println("Nie znaleziono klientów o podanych kryteriach!");
        }

    }

    public void printCategory(Optional<Category> category) {

        category.ifPresentOrElse(category1 -> {
            System.out.println("Nazwa kategorii: "+category1.getName() + "\nOpis kategorii: " + category1.getDescription());
            System.out.println("--------------------------------------------");
        }, () -> {
            System.err.println("Nie znalezniono kategorii o podanej nazwie!");
        });

    }


}
