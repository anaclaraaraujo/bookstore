import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<BookLoan> loans = new ArrayList<>();
    private List<User> users = new ArrayList<>();


    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<Book> listAllBooks() {
        return this.books;
    }

    public List<Book> booksAvailable() {
        List<Book> booksAvailable = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                booksAvailable.add(book);
            }
        }
        return booksAvailable;
    }

    public Book searchBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean updateBook(int id, String newTitle) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(newTitle);
                return true;
            }
        }
        return false;
    }

    public Book deleteBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                this.books.remove(book);
            }
        }
        return null;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public List<Author> listAllAuthors() {
        return authors;
    }

    public Author searchAuthorById(int id, String newName) {
        for (Author author : authors) {
            if (author.getId() == id) {
                author.setName(newName);
                break;
            }
        }
        return null;
    }

    public void removeAuthor(int id) {
        authors.removeIf(author -> author.getId() == id);
    }


    public void addUser(User User) {
        users.add(User);
    }

    public List<User> listAllUsers() {
        return users;
    }

    public User searchUserByEmail(String email) {
        if (users == null || users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return null;
        }

        email = email.trim();

        for (User user : users) {
            if (user.getEmail().trim().equalsIgnoreCase(email)) {
                return user;
            }
        }

        System.out.println("Email não encontrado: " + email);
        return null;
    }



    public void removeUser(int id) {
        users.removeIf(User -> User.getId() == id);
    }

    public void loanBook(Book book, User user) {
        if (book.isAvailable()) {
            BookLoan bookLoan = new BookLoan(book, user);
            loans.add(bookLoan);
            book.setStatus(false);
        }
    }

    public void devolutionBook(int idLoan) {
        for (BookLoan bookLoan : loans) {
            if (bookLoan.getId() == idLoan && bookLoan.isStatus()) {
                bookLoan.devolutionBook();
                bookLoan.getBook().setStatus(true);
                break;
            }
        }
    }

    public List<BookLoan> listLoanBooks() {
        return loans;
    }
}
