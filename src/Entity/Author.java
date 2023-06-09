package Entity;

import java.util.Objects;

public class Author {
    private int id;
    private String fname;
    private String lname;

    public Author(int id, String fname, String lname) {
        this(fname, lname);
        this.id = id;
    }

    public Author(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public Author() {

    }

    @Override
    public String toString() {
        return "Entity.Author{" +
                "id=" + id + '\'' +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getFname(), author.getFname()) && Objects.equals(getLname(), author.getLname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFname(), getLname());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

}
