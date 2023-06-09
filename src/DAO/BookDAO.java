package DAO;

import Entity.Author;
import Entity.Book;
import Entity.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IDAO<Book> {
    private String SELECT_ALL = "select book.id,title,year,fname,lname,genre from book " +
            "inner join author on " +
            "book.author_id = author.id " +
            "inner join genre on " +
            "book.genre_id = genre.id";
    private String SELECT_BY_KEY = "select book.id,title,year,fname,lname,genre from book " +
            "inner join author on " +
            "book.author_id = author.id " +
            "inner join genre on " +
            "book.genre_id = genre.id " +
            "where title = ? AND author_id = ? ";
    private String INSERT = "insert into book (title, year, author_id, genre_id) VALUES (?, ?, ?, ?)";

    @Override
    public List<Book> fetch() {
        List<Book> bookList = new ArrayList<>();
        Connection connection = DAOFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Book fetch(String value1, int value2) {
        Book book = new Book();
        Connection connection = DAOFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_KEY)) {
            statement.setString(1, value1);
            statement.setInt(2, value2);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public int insert(Book book) {
        IDAO<Genre> genreDao = new GenreDAO();
        int genre_id = genreDao.insert(new Genre(book.getGenre()));
        IDAO<Author> authorDao = new AuthorDAO();
        int author_id = authorDao.insert(new Author(book.getAuthorFname(), book.getAuthorLname()));
        Book bookAve = fetch(book.getTitle(), author_id);
        if (!bookAve.equals(new Book())) return bookAve.getId();
        int key = 0;

        Connection connection = DAOFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getYear());
            statement.setInt(3, author_id);
            statement.setInt(4, genre_id);
            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    key = generatedKeys.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    @Override
    public void update(Book book, Book tN) {

    }

    @Override
    public void delete(Book book) {

    }
}
