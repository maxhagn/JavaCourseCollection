package at.ac.tuwien.qs.movierental;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;

public class Validator {


    public String validateCustomer(Customer customer) {
        String errorMessage = "";

        if (customer.getFirstName().startsWith(" ") || customer.getFirstName().endsWith(" ")) {
            errorMessage += "Der Vorname darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (customer.getFirstName().length() < 3 || customer.getFirstName().length() > 250) {
            errorMessage += "Der Vorname muss zwischen 3 und 250 Zeichen lang sein.\n";
        }
        if (customer.getLastName().startsWith(" ") || customer.getLastName().endsWith(" ")) {
            errorMessage += "Der Nachname darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (customer.getLastName().length() < 3 || customer.getLastName().length() > 250) {
            errorMessage += "Der Nachname muss zwischen 3 und 250 Zeichen lang sein.\n";
        }
        if (!customer.getEmail().isEmpty() && !customer.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            errorMessage += "Die eingegebene Email Adresse ist ungültig.\n";
        }
        if (!customer.getPhone().isEmpty() && customer.getPhone().length() < 3 || customer.getPhone().length() > 250) {
            errorMessage += "Der Telefonnummer muss zwischen 3 und 250 Zeichen lang sein.\n";
        }
        if (customer.getAddress().startsWith(" ") || customer.getAddress().endsWith(" ")) {
            errorMessage += "Die Adresse darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (customer.getAddress().length() < 3 || customer.getAddress().length() > 1000) {
            errorMessage += "Die Adresse muss zwischen 3 und 1000 Zeichen lang sein.\n";
        }
        if (customer.getCity().startsWith(" ") || customer.getCity().endsWith(" ")) {
            errorMessage += "Die Stadt darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (customer.getCity().length() < 3 || customer.getCity().length() > 250) {
            errorMessage += "Die Stadt muss zwischen 3 und 250 Zeichen lang sein.\n";
        }
        if (customer.getZipCode().startsWith(" ") || customer.getZipCode().endsWith(" ")) {
            errorMessage += "Die Postleitzahl darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (customer.getZipCode().length() < 2 || customer.getZipCode().length() > 250) {
            errorMessage += "Die Postleitzahl muss zwischen 2 und 250 Zeichen lang sein.\n";
        }
        try {
            if (customer.getBirthday().isBefore(LocalDate.now().minusYears(120L)) || customer.getBirthday().isAfter(LocalDate.now())) {
                errorMessage += "Das eingegebene Geburtsdatum darf maximal 120 Jahre in der Vergangenheit liegen.\n";
            }
        } catch (DateTimeParseException e) {
            errorMessage += "Das eingegebene Geburtsdatum ist ungültig.\n";
        }

        return errorMessage;
    }

    public String validateMovie(Movie movie) {
        String errorMessage = "";
        if (movie.getTitle().startsWith(" ") || movie.getTitle().endsWith(" ")) {
            errorMessage += "Der Filmtitel darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (movie.getTitle().length() < 3 || movie.getTitle().length() > 250) {
            errorMessage += "Der Filmtitel muss zwischen 3 und 250 Zeichen lang sein.\n";
        }
        if (movie.getSubtitle() != null && !movie.getSubtitle().isEmpty() && (movie.getSubtitle().length() < 3 || movie.getSubtitle().length() > 250)) {
            errorMessage += "Der Subtitel darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (movie.getSubtitle() != null && !movie.getSubtitle().isEmpty() && movie.getSubtitle().length() < 3 || movie.getSubtitle().length() > 250) {
            errorMessage += "Der Subtitel muss zwischen 3 und 250 Zeichen lang sein.\n";
        }
        if (movie.getGenre() == null) {
            errorMessage += "Es muss ein Genre ausgewählt werden.\n";
        }
        if (movie.getAgeRating() == null) {
            errorMessage += "Es muss eine Altersfreigabe ausgewählt werden.\n";
        }
        if (movie.getLanguage() == null) {
            errorMessage += "Es muss eine Sprache ausgewählt werden.\n";
        }
        try {
            float rating = movie.getRating() != null ? movie.getRating() : -1;
            if (rating < 0 || rating > 5) {
                errorMessage += "Das Rating muss zwischen 0 und 5 liegen.\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Es muss ein gültiges Rating eingegeben werden.\n";
        }
        if (movie.getDirector() != null && !movie.getDirector().isEmpty() && (movie.getDirector().length() < 3 || movie.getDirector().length() > 250)) {
            errorMessage += "Der Name des Regisseur darf nicht mit einem Leerzeichen beginnen oder enden.\n";
        }
        if (movie.getDirector() != null && !movie.getDirector().isEmpty() && movie.getDirector().length() < 3 || movie.getDirector().length() > 250) {
            errorMessage += "Der Name des Regisseur muss zwischen 3 und 250 Zeichen lang sein.\n";
        }
        try {
            if (movie.getStock() < 0 || movie.getStock() > 50) {
                errorMessage += "Die Anzahl der verfügbaren Filme muss zwischen 0 und 50 liegen.\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Die Anzahl der verfügbaren Filme muss zwischen 0 und 50 liegen.\n";
        }
        return errorMessage;
    }
}
