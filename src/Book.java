import java.util.Date;

public class Book {
    private int id;
    private String title;
    private Author author;
    private Boolean status;
    private Date created_at;
    private Date updated_at;

    public Book(int id, String title, Author author, Boolean status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = true;
        this.created_at = new Date();
        this.updated_at = new Date();
    }

    public boolean isAvailable() {
        return this.status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updated_at = new Date();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author.getName() +
                ", status=" + (status ? "Disponível" : "Emprestado") +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
