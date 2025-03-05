import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookStore bookStore = new BookStore();
        Scanner scanner = new Scanner(System.in);

        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "J.K. Rowling", new Date(1965 - 1900, 6, 31)));
        authors.add(new Author(2, "George R.R. Martin", new Date(1948 - 1900, 8, 20)));
        authors.add(new Author(3, "J.R.R. Tolkien", new Date(1892 - 1900, 0, 3)));
        authors.add(new Author(4, "Agatha Christie", new Date(1890 - 1900, 8, 15)));
        authors.add(new Author(5, "Stephen King", new Date(1947 - 1900, 8, 21)));
        authors.add(new Author(6, "Arthur Conan Doyle", new Date(1859 - 1900, 4, 22)));
        authors.add(new Author(7, "Isaac Asimov", new Date(1920 - 1900, 0, 2)));
        authors.add(new Author(8, "Philip K. Dick", new Date(1928 - 1900, 11, 16)));
        authors.add(new Author(9, "Haruki Murakami", new Date(1949 - 1900, 0, 12)));
        authors.add(new Author(10, "Gabriel García Márquez", new Date(1927 - 1900, 2, 6)));

        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Harry Potter e a Pedra Filosofal", authors.get(0), true));
        books.add(new Book(2, "A Guerra dos Tronos", authors.get(1), true));
        books.add(new Book(3, "O Senhor dos Anéis", authors.get(2), true));

        for (Book book : books) {
            bookStore.addBook(book);
        }

        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice Johnson", new Date(1995 - 1900, 4, 12), "alice@email.com"));
        users.add(new User(2, "Bob Smith", new Date(1988 - 1900, 10, 25), "bob@email.com"));

        for (User user : users) {
            bookStore.addUser(user);
        }

        while(true) {
            System.out.print("Deseja ver os livros disponíveis? (sim/nao): ");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("sim")) {
                List<Book> booksAvailable = bookStore.booksAvailable();

                if (booksAvailable.isEmpty()) {
                    System.out.println("Não há livros disponíveis.");
                } else {
                    System.out.println("Livros disponíveis:");
                    for (Book book : booksAvailable) {
                        System.out.println(book.getId() + " - " + book.getTitle());
                    }

                    System.out.print("\nDigite o ID do livro que deseja emprestar: ");
                    int idBook = scanner.nextInt();
                    scanner.nextLine();
                    Book bookChoice = bookStore.searchBookById(idBook);

                    if (bookChoice != null && bookChoice.isAvailable()) {
                        System.out.print("Informe o seu email: ");

                        String email = scanner.nextLine().trim();
                        User emailChoice = bookStore.searchUserByEmail(email);

                        if (emailChoice != null) {
                            System.out.println("O livro " + bookChoice.getTitle() + " foi emprestado para " + emailChoice.getName() + "\n\n");
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                    } else {
                        System.out.println("Livro não encontrado ou não disponível para empréstimo.\n\n");
                    }
                }
            } else if (answer.equals("nao")) {
                System.out.println("Obrigado por utilizar o sistema da biblioteca.\n\n");
                break;
            } else {
                System.out.println("Resposta inválida. Por favor, responda com 'sim' ou 'não'.\n\n");
            }
        }

    }
}