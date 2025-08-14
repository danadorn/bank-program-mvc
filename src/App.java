import controller.AccountController;
import model.db.DbConnection;
import model.repository.AccountRepository;
import model.service.AccountService;
import util.Singleton;
import view.AccountView;

import static view.AccountView.scanner;

public class App {
    private static final AccountRepository repository = new AccountRepository(
//            DbConnection.getConnection()
    );

    private static final AccountController controller = Singleton.getControllerInstance();

    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    1. Create account
                    2. Find account by ID
                    3. Transfer money
                    4. View transaction details
                    5. Exit
                    """);
            System.out.print("Enter your choice: ");
            int op = Integer.parseInt(scanner.nextLine());

            if(op == 0) break;
            switch (op){
                case 1 -> controller.createAccount();
                case 2 -> controller.findAccountById();
                case 3 -> controller.transferMoney();
                case 4 -> controller.showTransactionRecords();
                default -> System.out.println("Invalid options!");

            }
        }
    }

}
