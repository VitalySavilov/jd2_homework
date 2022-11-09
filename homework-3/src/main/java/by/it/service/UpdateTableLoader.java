package by.it.service;

import by.it.dao.PersonUpdateDaoImpl;
import by.it.pojos.PersonUpdate;

import java.util.List;

public class UpdateTableLoader {
    public void load() {
        List<PersonUpdate> updateList = new PersonUpdateDaoImpl().findAll();
        if (updateList != null && updateList.size() != 0) {
            System.out.println("\nAll person_table updates:\n");
            System.out.printf("%-5s %-10s %-15s %-15s %-20s %-15s\n", "Id", "PersonId",
                    "PreviousName", "NewName", "Updater", "UpdateDate");
            for (PersonUpdate u : updateList) {
                System.out.printf("%-5s %-10s %-15s %-15s %-20s %-15s\n", u.getId(), u.getPersonId(),
                        u.getPreviousName(), u.getNewName(), u.getUpdater(), u.getUpdateDate());
            }
        }
    }
}
