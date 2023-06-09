package Entity;

import java.util.Objects;

public class Genre {
    private int id;
    private String genre;

    public Genre(int id, String genre) {
        this(genre);
        this.id = id;
    }

    public Genre() {
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre1 = (Genre) o;
        return getId() == genre1.getId() && Objects.equals(getGenre(), genre1.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGenre());
    }

    public Genre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }
}
