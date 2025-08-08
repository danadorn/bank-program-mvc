import controller.AccountController;
import model.db.DbConnection;
import model.repository.AccountRepository;
import model.service.AccountService;
import view.AccountView;

import static view.AccountView.scanner;

public class App {
    private static final AccountRepository repository = new AccountRepository(
            DbConnection.getConnection()
    );

    private static AccountService service = new AccountService(repository);
    private static AccountView view = new AccountView();
    private static AccountController controller = new AccountController(view, service);

    public void main(String[] args) {
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
        }
    }

}
