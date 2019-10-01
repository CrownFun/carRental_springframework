package pl.filewicz.controllers.searching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.printing.PrintText;
import pl.filewicz.model.Client;
import pl.filewicz.repository.ClientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ClientSearching implements SearchCriteria {

    private Scanner scanner;
    private ClientRepository clientRepository;
    private PrintText printText;


    @Autowired
    public ClientSearching(Scanner scanner, ClientRepository clientRepository, PrintText printText) {
        this.scanner = scanner;
        this.clientRepository = clientRepository;
        this.printText = printText;
    }

    public Optional<Client> getClientByPhoneNumber() {
        System.out.println("Podaj numer telefonu klienta");
        return clientRepository.findAllByPhoneNumber(scanner.nextLong());

    }

    private List<Client> getClientsByLastName() {
        System.out.println("Podaj nazwisko klienta");
        return clientRepository.findBylastName(scanner.nextLine().toUpperCase());
    }


    private List<Client> getClientByAddres() {
        System.out.println("Podaj adres klienta");
        return clientRepository.findAllByAddresContaining(scanner.nextLine().toUpperCase());
    }

    @Override
    public void chooseSearchCriteria() {
        System.out.println("Wybierz kryterium wyszukiwania:");
        Arrays.stream(ClientSearchCriteria.values()).forEach(System.out::println);
        ClientSearchCriteria searchType = ClientSearchCriteria.values()[scanner.nextInt()-1];
        scanner.nextLine();
        switch (searchType) {
            case FIND_BY_PHONE: {
                printText.printClient(getClientByPhoneNumber());
                break;
            }
            case FIND_BY_ADDRES: {
                printText.printListOfClients(getClientByAddres());
                break;
            }
            case FIND_BY_lASTNAME: {
                printText.printListOfClients(getClientsByLastName());
                break;
            }
        }

    }


}
