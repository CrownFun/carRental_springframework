package pl.filewicz.controllers.searching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.printing.PrintText;
import pl.filewicz.model.Category;
import pl.filewicz.repository.CategoryRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CategorySearching implements SearchCriteria {


    private Scanner scanner;
    private CategoryRepository categoryRepository;
    private PrintText printText;

    @Autowired
    public CategorySearching(Scanner scanner, CategoryRepository categoryRepository, PrintText printText) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
        this.printText = printText;
    }

    @Override
    public void chooseSearchCriteria() {
        System.out.println("Podaj nazwę kategorii");
        Optional<Category> category = categoryRepository.findByName(scanner.nextLine());
        printText.printCategory(category);
    }




}
