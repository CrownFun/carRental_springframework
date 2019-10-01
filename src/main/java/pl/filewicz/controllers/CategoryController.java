package pl.filewicz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controllers.searching.CategorySearching;
import pl.filewicz.model.Category;
import pl.filewicz.repository.CategoryRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryController {

    private Scanner scanner;
    private CategoryRepository categoryRepository;
    private CategorySearching categorySearching;

    @Autowired
    public CategoryController(Scanner scanner, CategoryRepository categoryRepository, CategorySearching categorySearching) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
        this.categorySearching = categorySearching;
    }

    public Category createCategory() {

        Category category = null;
        try {
            String categoryName = getNameCategory();
            System.out.println("Podaj opis kategorii");
            String categoryDescription = scanner.nextLine();
            category = new Category(categoryName, categoryDescription);
            categoryRepository.save(category);

        } catch (Exception e) {
            System.err.println("Nie udało się dodać nowej kategorii!");
        }

        return category;
    }

    public void readCategory() {
        categorySearching.chooseSearchCriteria();
    }

    public void deleteCategory() {
        String categoryName = getNameCategory();
        Optional<Category> category = categoryRepository.findByName(categoryName);
        category.ifPresentOrElse(category1 -> {
            categoryRepository.delete(category1);
            System.out.println("Pomyślnie usunięto");
        }, () -> {
            System.err.println("Nie udało się usunąć kategorii!");
        });
    }

    private String getNameCategory() {
        System.out.println("Podaj nazwę kategorii");
        return scanner.nextLine().toUpperCase();
    }


}
