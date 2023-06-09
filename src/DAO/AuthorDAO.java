package DAO;

import Entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements IDAO<Author> {

    private String  SELECT_ALL = "select * from author";
    private String  SELECT_BY = "select * from author where FName = ? and LName = ?";
    private String  INSERT = "insert into author (fname, lname) VALUES (?, ?)";
    private String  DELETE = "delete from author where (fname = ? and lname = ?)";
    private String  UPDATE = "update author set fname = ?, lname = ? " +
            "where (fname = ? and lname = ?)";

    @Override
    public List<Author> fetch() {
        List<Author> authorList = new ArrayList<>();
        Connection connection = DAOFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                authorList.add(new Author(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }
    public Author fetch(String FName, String LName) {
        Author author = new Author();
        Connection connection = DAOFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY)) {
            statement.setString(1, FName);
            statement.setString(2, LName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                author = new Author(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
    @Override
    public int insert(Author author) {
        Author authorAve = fetch(author.getFname(), author.getLname());
        if(!authorAve.equals(new Author())) return authorAve.getId();
        int key = 0;

        Connection connection = DAOFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getFname());
            statement.setString(2, author.getLname());
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
    public void update(Author author, Author authorNew) {
        if(!fetch(authorNew.getFname(), authorNew.getLname()).equals(new Author())) {
            System.out.println("New author is already available in the table.");
            return;
        };

        if (!author.equals(authorNew)){
            Connection connection = DAOFactory.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                statement.setString(1, authorNew.getFname());
                statement.setString(2, authorNew.getLname());
                statement.setString(3, author.getFname());
                statement.setString(4, author.getLname());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void delete(Author author) {
        Connection connection = DAOFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setString(1, author.getFname());
            statement.setString(2, author.getLname());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
