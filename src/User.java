import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String name;
    private Date birthDate;
    private String email;
    private List<Loan> loanHistory;

    public User(int id, String name, Date birthDate, String email) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.loanHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public List<Loan> getLoanHistory() {
        return loanHistory;
    }

    public void addLoan(Loan loan) {
        loanHistory.add(loan);
    }

    @Override
    public String toString() {
        return "Usuário: " + name + " | Email: " + email;
    }
}