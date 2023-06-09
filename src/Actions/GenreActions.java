package Actions;

import DAO.GenreDAO;
import DAO.IDAO;
import Entity.Genre;

import java.util.Arrays;
import java.util.List;

public class GenreActions {
    public static void genreActions() {
        IDAO<Genre> genreDao = new GenreDAO();
        List<Genre> genreList = Arrays.asList(new Genre("Sci-fi"),
                new Genre("Thriller"));
        for (Genre genre : genreList) {
            genre.setId(genreDao.insert(genre));
        }
        genreDao.fetch().forEach(System.out::println);
    }
}
