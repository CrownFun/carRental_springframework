package pl.filewicz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.searching.ClientSearching;
import pl.filewicz.model.Client;
import pl.filewicz.repository.ClientRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class ClientController {

    private Scanner scanner;
    private ClientRepository clientRepository;
    private ClientSearching clientSearching;


    @Autowired
    public ClientController(Scanner scanner, ClientRepository clientRepository, ClientSearching clientSearching) {
        this.scanner = scanner;
        this.clientRepository = clientRepository;
        this.clientSearching = clientSearching;
    }

    public void createNewClient() {

        try {
            System.out.println("Podaj imie klienta");
            String name = scanner.nextLine().toUpperCase();
            System.out.println("Podaj nazwisko klienta");
            String lastName = scanner.nextLine().toUpperCase();
            System.out.println("Podaj numer telefonu klienta");
            long phoneNumber = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Podaj adres klienta");
            String adres = scanner.nextLine().toUpperCase();

            Client client = new Client(name, lastName, phoneNumber, adres);

            clientRepository.save(client);

        } catch (Exception e) {
            System.err.println("Nie udało się stworzyć nowego klienta!");
        }

    }

    public void readClient() {
        clientSearching.chooseSearchCriteria();
    }

    public void deleteClient() {
        Optional<Client> client = clientSearching.getClientByPhoneNumber();
        client.ifPresentOrElse(value -> {
            clientRepository.delete(value);
            System.out.println("Pomyślnie usunięto");
        }, () -> System.err.println("Nie udało się usunąć klienta!"));
    }


}
