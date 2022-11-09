package by.it.manager;

import by.it.service.PersonLoader;
import by.it.service.UpdateTableLoader;

public class ManagePerson {
    public static void main(String[] args) {
        new PersonLoader().load();
        new UpdateTableLoader().load();
        System.out.println("\nGoodbye!");
    }
}
