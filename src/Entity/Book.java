package Entity;

import java.util.Date;
import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String year;
    private String authorFname;
    private String authorLname;
    private String genre;

    public Book(int id, String title, String year, String authorFname, String authorLname, String genre) {
        this(title, year, authorFname, authorLname, genre);
        this.id = id;
    }

    public Book(String title, String year, String authorFname, String authorLname, String genre) {
        this.title = title;
        this.year = year;
        this.authorFname = authorFname;
        this.authorLname = authorLname;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getYear(), book.getYear()) && Objects.equals(getAuthorFname(), book.getAuthorFname()) && Objects.equals(getAuthorLname(), book.getAuthorLname()) && Objects.equals(getGenre(), book.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getYear(), getAuthorFname(), getAuthorLname(), getGenre());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", authorFname='" + authorFname + '\'' +
                ", authorLname='" + authorLname + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAuthorFname() {
        return authorFname;
    }

    public String getAuthorLname() {
        return authorLname;
    }

    public String getGenre() {
        return genre;
    }
}
