package Account;

import CarSystem.CarManager;
import CarSystem.CarSystem;
import IOTextFile.IOTextFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserLoginManager implements Serializable {

    IOTextFile<User> ioTextFile = new IOTextFile();
    public ArrayList<User> users;
    CarSystem carSystem = new CarSystem();

    public UserLoginManager() {
        users = ioTextFile.readFile("src/File/UserAccount.txt");
        User admin = new User("minh", "123");
        admin.setRole1(new Role("ADMIN"));
        users.add(admin);
        ioTextFile.writeFile(users, "src/File/UserAccount.txt");
    }

    public void loginAccount(Scanner scanner) {
        users = ioTextFile.readFile("src/File/UserAccount.txt");
        try {
            System.out.println("Enter account: ");
            String account = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            for (User user : users) {
                if (user.getAccount().equals(account) && user.getPassword().equals(password)) {
                    if (user.getRole1().getName_role().equals("ADMIN")) {
                        System.out.println("✅ " + "Successfully login!");
                        carSystem.menuAdmin(scanner, new CarManager());
                    } else {
                        System.out.println("✅ " + "Successfully login!");
                        carSystem.menuUser(scanner, new CarManager());
                    }
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }
    public void registerAccount(Scanner scanner) {
        try {
            System.out.println("Enter name account: ");
            String account = scanner.nextLine();
            System.out.println("Enter password account: ");
            String password = scanner.nextLine();
            User user = new User(account, password);
            user.setRole1(new Role("USER"));
            users.add(user);
            user.setId(user.getId());
            System.out.println("✅ " + "Successfully register!");
            ioTextFile.writeFile(users, "src/File/UserAccount.txt");
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }
}
