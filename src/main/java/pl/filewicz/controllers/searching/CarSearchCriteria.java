package pl.filewicz.controllers.searching;

public enum CarSearchCriteria {

    FIND_BY_BRAND(1, "Szukaj według marki"),
    FIND_BY_YEAR(2, "Szukaj według roku produkcji"),
    FIND_BY_COLOR(3, "Szukaj według koloru"),
    FIND_BY_VIN(4, "Szukaj według numeru VIN"),
    FIND_BY_CATEGORY(5,"Szukaj według kategorii");


    private int id;
    private String name;

    CarSearchCriteria(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}