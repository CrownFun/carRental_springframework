package pl.filewicz.controllers.searching;

public enum ClientSearchCriteria {

    FIND_BY_lASTNAME(1, "Szukaj wg nazwiska"),
    FIND_BY_PHONE(2, "Szukaj wg numeru telefonu"),
    FIND_BY_ADDRES(3, "Szukaj wg adresu");


    private int id;
    private String name;

    ClientSearchCriteria(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
