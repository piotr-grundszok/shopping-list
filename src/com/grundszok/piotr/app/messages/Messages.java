package com.grundszok.piotr.app.messages;

public final class Messages {
    public static final String INVALID_LIST = "Twoja lista jest pusta!";
    public static final String GREETING_MESSAGE = "Witaj!\n" +
            "Wybierz ustawienie wyswietlania\n" +
            "Dostepne opcje: + , = , *\n" +
            "Zeby wyjsc wproadz: ";
    public static final String INVALID_CHARACTER = "Niepoprawny znak dla typu wyświetlania";
    public static final String FIRST_PRODUCT = "Podaj pierwszy produkt:";
    public static final String NEXT_PRODUCT = "\n\nWprowadz dowolny przycisk zeby przejsc do menu";
    public static final String CONFIRM_CLEAR_LIST = "Czy jestes pewien ze chcesz wyczyscic liste zakupow? Jesli tak, wpisz \"";
    public static final String CLEAR_CONFIRMED = "Wyczyszczono liste zakupów";
    public static final String SAVED_TO_FILE_SIZE = "Zapisano %d elementów do pliku";
    public static final String MENU_ITEMS =
            "1. Stworz liste zakupow\n"
                    + "2. Wyświetl liste zakupow\n"
                    + "3. Wyczyść liste zakupow\n"
                    + "4. Zmien styl wyświetlania\n"
                    + "5. Zapisz listę zakupow do pliku\n"
                    + "6. Wczytaj listę zakupów z pliku\n\n"
                    + "X. Zakoncz program";
    public static final String GOODBYE = "Udanych zakupów!";
    public static final String SAVING_ERROR = "Problem z zapisem do pliku!";
    public static final String CHOOSE_LIST_NUMBER = "Wybierz numer listy, którą chcesz wczytać:";
    public static final String INVALID_NUMBER = "Niepoprawny numer, spróbuj ponownie";
    public static final String USER_CONFIRMATION_MALE = "\nJeśli tak wprowadź \"T\", jeśli nie" +
            "wprowdz \"N\"";
    public static final String SAVE_LIST_CONFIRMATION = "Czy chcesz zapisać aktualną listę?";
    public static final String UNAPPROVED_USER_INPUT = "Niepoprawny wybór, spróbuj ponownie";
    public static final String CLEAR_LIST_CONFIRMATION = "Czy chcesz wyczyścić obecną listę?";
    public static final String GIVE_AMMOUNT = "Podaj ilość:";
    public static final String ANOTHER_PRODUCT = "Dodano %s. \nPodaj kolejny produkt albo wpisz \"X\" żeby wyjść:";

    private Messages() {
    }
}
