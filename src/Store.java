import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("⚠️ Nenhum livro cadastrado.");
        } else {
            System.out.println("\n===== Lista de Livros =====");
            for (Book book : books) {
                System.out.println("ID: " + book.getId() + " - Título: " + book.getTitle() + " - Autor: " + book.getAuthor().getName() + " - Disponível: " + (book.isAvailable() ? "Sim" : "Não"));
            }
        }
    }


    public void showAuthors() {
        authors.forEach(System.out::println);
    }

    public void showUsers() {
        users.forEach(System.out::println);
    }

    public void showLoans() {
        loans.forEach(System.out::println);
    }

    public void loanBook(Book book, User user, Date loanDate, Date dueDate) {
        if (book.isAvailable()) {
            Loan loan = new Loan(book, user, loanDate, dueDate);
            loans.add(loan);
            user.addLoan(loan); // Adiciona o empréstimo ao histórico do usuário
            book.setAvailable(false);
            System.out.println("✅ Livro emprestado com sucesso!");
        } else {
            System.out.println("⚠️ O livro já está emprestado.");
        }
    }

    public void showLoanHistory(User user) {
        if (user.getLoanHistory().isEmpty()) {
            System.out.println("⚠️ O usuário não possui histórico de empréstimos.");
        } else {
            System.out.println("\n===== Histórico de Empréstimos de " + user.getName() + " =====");
            for (Loan loan : user.getLoanHistory()) {
                System.out.println(loan);
            }
        }
    }

    public void returnBook(Book book, Date returnDate) {
        Loan loan = loans.stream()
                .filter(l -> l.getBook().equals(book) && l.getReturnDate() == null)
                .findFirst()
                .orElse(null);
        if (loan != null) {
            loan.returnBook(returnDate);
            book.setAvailable(true);
            System.out.println("✅ Livro devolvido com sucesso!");
        } else {
            System.out.println("⚠️ Nenhum empréstimo ativo encontrado para este livro.");
        }
    }


    public List<User> listAllUsers() {
        return users;
    }

    public List<Author> listAllAuthors() {
        return authors;
    }

    public List<Book> booksAvailable() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Book> listLoanBooks() {
        return books.stream().filter(book -> !book.isAvailable()).collect(Collectors.toList());
    }

    public Book searchBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public User searchUserByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    public void populateData() {
        // Adicionando autores
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

        // Adicionando livros
        books.add(new Book(1, "Harry Potter e a Pedra Filosofal", authors.get(0), true));
        books.add(new Book(2, "Harry Potter e a Câmara Secreta", authors.get(0), true));
        books.add(new Book(3, "A Guerra dos Tronos", authors.get(1), true));
        books.add(new Book(4, "O Senhor dos Anéis: A Sociedade do Anel", authors.get(2), true));
        books.add(new Book(5, "O Senhor dos Anéis: As Duas Torres", authors.get(2), true));
        books.add(new Book(6, "O Assassinato no Expresso do Oriente", authors.get(3), true));
        books.add(new Book(7, "E Não Sobrou Nenhum", authors.get(3), true));
        books.add(new Book(8, "O Iluminado", authors.get(4), true));
        books.add(new Book(9, "It: A Coisa", authors.get(4), true));
        books.add(new Book(10, "Sherlock Holmes: Um Estudo em Vermelho", authors.get(5), true));
        books.add(new Book(11, "Sherlock Holmes: O Cão dos Baskervilles", authors.get(5), true));
        books.add(new Book(12, "Fundação", authors.get(6), true));
        books.add(new Book(13, "Eu, Robô", authors.get(6), true));
        books.add(new Book(14, "Androides Sonham com Ovelhas Elétricas?", authors.get(7), true));
        books.add(new Book(15, "1Q84", authors.get(8), true));
        books.add(new Book(16, "Norwegian Wood", authors.get(8), true));
        books.add(new Book(17, "Cem Anos de Solidão", authors.get(9), true));
        books.add(new Book(18, "O Amor nos Tempos do Cólera", authors.get(9), true));

        // Adicionando usuários
        users.add(new User(1, "Alice Johnson", new Date(1995 - 1900, 4, 12), "alice@email.com"));
        users.add(new User(2, "Bob Smith", new Date(1988 - 1900, 10, 25), "bob@email.com"));
        users.add(new User(3, "Carol Davis", new Date(1992 - 1900, 2, 8), "carol@email.com"));
        users.add(new User(4, "David Wilson", new Date(1985 - 1900, 6, 14), "david@email.com"));
    }
}