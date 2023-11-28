package mytimeorganizer.models;

import java.time.LocalDate;

public class Note {

    public static Long USER_ID;

    private Long id;
    private LocalDate date;
    private String description;

    public static Long getUserId() {
        return USER_ID;
    }

    public static void setUserId(Long userId) {
        USER_ID = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
