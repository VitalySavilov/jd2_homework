package by.it.service;

import by.it.dao.PersonDaoImpl;
import by.it.pojos.Person;

import java.util.Scanner;

public class PersonLoader {

    private final PersonDaoImpl personDao = new PersonDaoImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void load() {
        while (true) {
            System.out.println("Please choose command:\n" +
                    "1. Delete Person\n" +
                    "2. Find Person with get()\n" +
                    "3. Find Person with load()\n" +
                    "4. Save Person\n" +
                    "5. Flush Person\n" +
                    "6. Refresh Person\n" +
                    "7. Create-Delete Person\n" +
                    "8. Exit");
            int num = scanner.nextInt();
            System.out.println("Your choose - " + num);
            scanner.nextLine();
            if (num == 1) personDao.delete(personDao.getById(initPersonId()));
            else if (num == 2) personDao.getById(initPersonId());
            else if (num == 3) personDao.loadById(initPersonId());
            else if (num == 4) personDao.create(Person.builder()
                    .name(initPersonName())
                    .surname(initPersonSurname())
                    .age(initPersonAge())
                    .build());
            else if (num == 5) personDao.flush(initPersonId(), initPersonName());
            else if (num == 6) personDao.refresh(initPersonId(), initPersonName());
            else if (num == 7) personDao.delete();
            else if (num == 8) return;
            else System.out.println("Unknown command");
        }
    }

    private long initPersonId() {
        System.out.println("Please insert Person ID:");
        long id = scanner.nextLong();
        scanner.nextLine();
        return id;
    }

    private String initPersonName() {
        System.out.println("Please insert Person name:");
        return scanner.nextLine();
    }

    private String initPersonSurname() {
        System.out.println("Please insert Person surname:");
        return scanner.nextLine();
    }

    private int initPersonAge() {
        System.out.println("Please insert Person age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        return age;
    }
}
