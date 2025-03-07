import java.util.Date;

public class Loan {
    private Book book;
    private User user;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    public Loan(Book book, User user, Date loanDate, Date dueDate) {
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        book.setAvailable(false); // Marca o livro como emprestado
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void returnBook(Date returnDate) {
        this.returnDate = returnDate;
        book.setAvailable(true);
    }

    @Override
    public String toString() {
        return "Empréstimo: Livro [" + book.getTitle() + "] | Usuário: " + user.getName() + " | Data de Devolução: " + (returnDate != null ? returnDate : "Ainda não devolvido");
    }
}