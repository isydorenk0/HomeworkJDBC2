package DAO;

import Entity.Author;
import Entity.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements IDAO<Genre>{
    private final String INSERT = "insert into genre (genre) VALUES (?)";
    private String  SELECT_ALL = "select * from genre";
    private String  SELECT_BY = "select * from genre where genre = ?";
    @Override
    public List<Genre> fetch() {
        List<Genre> genreList = new ArrayList<>();
        Connection connection = DAOFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                genreList.add(new Genre(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreList;
    }

    public Genre fetch(String genreName) {
        Genre genre = new Genre();
        Connection connection = DAOFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY)) {
            statement.setString(1, genreName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    @Override
    public int insert(Genre genre) {
        Genre genreAve = fetch(genre.getGenre());
        if(!genreAve.equals(new Genre())) return genreAve.getId();
        int key = 0;

        Connection connection = DAOFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, genre.getGenre());
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
    public void update(Genre genre, Genre tN) {

    }

    @Override
    public void delete(Genre genre) {

    }
}
