package lexicon;

public class Subscriber {

    private int id;
    private String email;
    private Plan plan;
    private boolean active;
    private int monthsRemaining;

    public Subscriber(int id, String email, boolean active, Plan plan, int monthsRemaining) {
        this.id = id;
        this.email = email;
        this.active = active;
        this.plan = plan;
        this.monthsRemaining = monthsRemaining;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Plan getPlan() {
        return plan;
    }

    public boolean isActive() {
        return active;
    }

    public int getMonthsRemaining() {
        return monthsRemaining;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setMonthsRemaining(int monthsRemaining) {
        this.monthsRemaining = monthsRemaining;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", plan=" + plan +
                ", active=" + active +
                ", monthsRemaining=" + monthsRemaining +
                '}';
    }
}
