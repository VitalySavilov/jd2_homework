package task_6.service;

import task_6.dao.ClientDao;
import task_6.dao.CourseDao;
import task_6.dao.PaymentDao;
import task_6.model.Client;
import task_6.model.Course;
import task_6.model.Payment;

import java.util.Scanner;

public class GeneratorDemonstrator {
    private final Scanner scanner = new Scanner(System.in);

    public void load() {
        System.out.println("Welcome to ID-generator demonstrator");
        while (true) {
            System.out.println("Please choose command:\n" +
                    "1. Demonstrate SEQUENCE strategy\n" +
                    "2. Demonstrate IDENTITY strategy\n" +
                    "3. Demonstrate TABLE strategy\n" +
                    "4. Exit");
            int num = scanner.nextInt();
            System.out.println("Your choose - " + num);
            scanner.nextLine();
            if (num == 1) new ClientDao().savePrintId(Client.builder().build());
            else if (num == 2) new CourseDao().savePrintId(Course.builder().build());
            else if (num == 3) new PaymentDao().savePrintId(Payment.builder().build());
            else if (num == 4) return;
            else System.out.println("Unknown command\n");
        }
    }
}
