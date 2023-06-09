import DAO.DAOFactory;

import static Actions.AuthorActions.authorActions;
import static Actions.BookActions.bookActions;
import static Actions.GenreActions.genreActions;

public class Main {
    public static void main(String[] args) {
        DAOFactory.getInstance();
        authorActions();
        genreActions();
        bookActions();
    }
}