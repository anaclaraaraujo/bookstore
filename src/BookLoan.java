import java.util.Date;

public class BookLoan {
    private int id = 0;
    private Book book;
    private User user;
    private boolean status;
    private Date created_at;
    private Date devolution_date;

    BookLoan(int id, Book book, User user, boolean status, Date created_at, Date devolution_date) {
        this.id = id++;
        this.book = book;
        this.user = user;
        this.status = status;
        this.created_at = created_at;
        this.devolution_date = devolution_date;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getDevolution_date() {
        return devolution_date;
    }

    public void devolutionBook() {
        this.devolution_date = new Date();
        this.status = false;
        this.book.isAvailable();
    }

    public BookLoan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.created_at = new Date();
        this.devolution_date = new Date();
    }
}
