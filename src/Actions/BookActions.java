package Actions;

import DAO.BookDAO;
import DAO.IDAO;
import Entity.Book;

import java.util.Arrays;
import java.util.List;

public class BookActions {
    public static void bookActions() {
        IDAO<Book> bookDao = new BookDAO();
        List<Book> bookList = Arrays.asList(new Book("Yesterday", "1965", "Paul", "McCartney", "Romance"),
                new Book("Imagine", "1971", "John", "Lennon", "Sci-fi"));
        for (Book book : bookList) {
            book.setId(bookDao.insert(book));
        }
        bookDao.fetch().forEach(System.out::println);
    }
}
