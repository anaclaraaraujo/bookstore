import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);

        store.populateData();

        while (true) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            MenuOption menuOption = MenuOption.fromInt(option);

            if (menuOption == null) {
                System.out.println("❌ Opção inválida! Tente novamente.");
                continue;
            }

            switch (menuOption) {
                case LIST_ALL_BOOKS:
                    store.showBooks();
                    showSubmenu(scanner);
                    break;

                case LIST_AVAILABLE_BOOKS:
                    store.booksAvailable().forEach(System.out::println);
                    showSubmenu(scanner);
                    break;

                case LIST_LOANED_BOOKS:
                    store.listLoanBooks().forEach(System.out::println);
                    showSubmenu(scanner);
                    break;

                case LOAN_BOOK:
                    System.out.print("📧 Digite seu email: ");
                    String email = scanner.nextLine();
                    User user = store.searchUserByEmail(email);
                    if (user != null) {
                        System.out.print("📚 Digite o ID do livro: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();
                        Book book = store.searchBookById(bookId);
                        if (book != null && book.isAvailable()) {
                            store.loanBook(book, user, new Date(), new Date(System.currentTimeMillis() + (7L * 24 * 60 * 60 * 1000))); // Devolução em 7 dias
                            System.out.println("✅ Livro emprestado com sucesso!");
                        } else {
                            System.out.println("⚠️ Livro não disponível.");
                        }
                    } else {
                        System.out.println("⚠️ Usuário não encontrado.");
                    }
                    break;

                case RETURN_BOOK:
                    System.out.print("🔄 Digite o ID do livro para devolução: ");
                    int bookReturnId = scanner.nextInt();
                    scanner.nextLine();
                    Book bookToReturn = store.searchBookById(bookReturnId);
                    if (bookToReturn != null) {
                        store.returnBook(bookToReturn, new Date());
                        System.out.println("✅ Livro devolvido com sucesso!");
                    } else {
                        System.out.println("⚠️ Livro não encontrado.");
                    }
                    break;

                case REGISTER_USER:
                    System.out.print("📝 Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("📧 Email: ");
                    String newUserEmail = scanner.nextLine();
                    User newUser = new User(store.listAllUsers().size() + 1, name, new Date(), newUserEmail);
                    store.addUser(newUser);
                    System.out.println("✅ Usuário cadastrado com sucesso!");
                    break;

                case LIST_AUTHORS:
                    store.listAllAuthors().forEach(System.out::println);
                    showSubmenu(scanner);
                    break;

                case SHOW_LOAN_HISTORY:
                    System.out.print("📧 Digite seu email para ver o histórico de empréstimos: ");
                    String emailForHistory = scanner.nextLine();
                    User userForHistory = store.searchUserByEmail(emailForHistory);
                    if (userForHistory != null) {
                        store.showLoanHistory(userForHistory);
                    } else {
                        System.out.println("⚠️ Usuário não encontrado.");
                    }
                    break;


                case EXIT:
                    System.out.println("👋 Saindo do sistema...");
                    scanner.close();
                    return;
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== 📖 MENU PRINCIPAL =====");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.ordinal() + 1 + ". " + option.getDescription());
        }
        System.out.print("Escolha uma opção: ");
    }

    private static void showSubmenu(Scanner scanner) {
        while (true) {
            showSubmenuOptions();
            int submenuOption = scanner.nextInt();
            scanner.nextLine();

            if (submenuOption == 1) {
                break; // Retorna para o menu principal
            } else if (submenuOption == 2) {
                System.out.println("👋 Saindo do sistema...");
                scanner.close();
                System.exit(0); // Sai do sistema
            } else {
                System.out.println("❌ Opção inválida! Tente novamente.");
            }
        }
    }

    private static void showSubmenuOptions() {
        System.out.println("\n===== 📖 SUBMENU =====");
        System.out.println("1. Retornar ao Menu");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public enum MenuOption {
        LIST_ALL_BOOKS(1, "Listar todos os livros"),
        LIST_AVAILABLE_BOOKS(2, "Listar livros disponíveis"),
        LIST_LOANED_BOOKS(3, "Listar livros emprestados"),
        LOAN_BOOK(4, "Emprestar um livro"),
        RETURN_BOOK(5, "Devolver um livro"),
        REGISTER_USER(6, "Cadastrar usuário"),
        LIST_AUTHORS(7, "Listar autores"),
        SHOW_LOAN_HISTORY(8, "Ver histórico de empréstimos"),
        EXIT(9, "Sair");

        private final int value;
        private final String description;

        MenuOption(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public static MenuOption fromInt(int option) {
            for (MenuOption op : MenuOption.values()) {
                if (op.value == option) {
                    return op;
                }
            }
            return null;
        }

        public String getDescription() {
            return description;
        }
    }
}