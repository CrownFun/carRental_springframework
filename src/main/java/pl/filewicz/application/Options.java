package pl.filewicz.application;


    enum Options {
        ADD_CAR(1, "Dodaj samochód"),
        ADD_CATEGORY(2, "Dodaj kategorię"),
        ADD_CLIENT(3, "Dodaj klienta"),
        RENT(4, "Wypożycz samochód"),
        REMOVE_CAR(5, "Usuń samochód"),
        REMOVE_CATEGORY(6, "Usuń kategorię"),
        REMOVE_CLIENT(7, "Usuń klienta"),
        FIND_CLIENT(8,"Szukaj klienta"),
        FIND_CATEGORY(9,"Szukaj kategorii"),
        FIND_CAR(10,"Szukaj samochodu"),
        EXIT(11, "Wyjście");

        private int number;
        private String name;

        Options(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
         return number + " - "+name;
        }
    }
