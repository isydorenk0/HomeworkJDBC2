package Actions;

import DAO.AuthorDAO;
import DAO.IDAO;
import Entity.Author;

import java.util.Arrays;
import java.util.List;

public class AuthorActions {
    public static void authorActions() {
        IDAO<Author> authorDao = new AuthorDAO();
        List<Author> authorList = Arrays.asList(new Author("John", "Lennon"),
                new Author("John", "Lennon"),     // Test duplicate
                new Author("John", "McCartney"),
                new Author("Ringo", "Starr"));
        for (Author author : authorList) {
            author.setId(authorDao.insert(author));
        }
        authorDao.fetch().forEach(System.out::println);

        authorDao.delete(authorList.get(0)); // delete Lennon
        authorDao.fetch().forEach(System.out::println);

        Author authorCorrection = new Author("Paul", "McCartney");
        authorDao.update(authorList.get(2), authorCorrection);
        authorDao.fetch().forEach(System.out::println);
    }
}
