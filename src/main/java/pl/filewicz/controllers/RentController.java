package pl.filewicz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.searching.CarSearching;
import pl.filewicz.controllers.searching.ClientSearching;
import pl.filewicz.model.Car;
import pl.filewicz.model.Client;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RentController {


    private ClientSearching clientSearching;
    private CarSearching carSearching;

    @Autowired
    public RentController(ClientSearching clientSearching, CarSearching carSearching) {
        this.clientSearching = clientSearching;
        this.carSearching = carSearching;
    }

    @Transactional
    public void rent() {

        try {
            Optional<Car> car = carSearching.searchCarByVinNumber();
            Optional<Client> client = clientSearching.getClientByPhoneNumber();
            client.ifPresentOrElse(client1 -> car.ifPresentOrElse(value -> {
                value.addClient(client1);
                client1.addCars(value);
            }, () -> {
                System.err.println("Nie znaleziono samochodu o podanym numerze VIN!");
            }), () -> {
                System.err.println("Nie znaleziono klienta o podanym numerze telefonu!");
            });

            System.out.println("Wypozyczono samochod " + car.get().getBrand() + " "+car.get().getModel()+ " klientowi " + client.get().getName()+" "+client.get().getLastName());
        } catch (Exception e) {
            System.err.println("Nie udało się wypożyczyć samochodu!");
        }
    }


}
