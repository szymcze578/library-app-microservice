package org.szymon.publication.Services;


import org.springframework.stereotype.Service;
import org.szymon.publication.DataTransferObjects.BookViewModel;
import org.szymon.publication.DataTransferObjects.Library.LibraryData;
import org.szymon.publication.DataTransferObjects.Library.LibraryViewModel;
import org.szymon.publication.DataTransferObjects.MagazineViewModel;
import org.szymon.publication.Interfaces.IBookService;
import org.szymon.publication.Interfaces.ILibraryService;
import org.szymon.publication.Interfaces.IMagazineService;

import java.util.List;

@Service
public class LibraryService implements ILibraryService {

    private final IBookService bookService;
    private final IMagazineService magazineService;

    public LibraryService(IBookService bookService, IMagazineService magazineService) {
        this.bookService = bookService;
        this.magazineService = magazineService;
    }

    public LibraryViewModel getAllPublications(String type, boolean includeStats) {
        var books = type == null || type.equalsIgnoreCase("book")
                ? bookService.getBooks()
                : List.<BookViewModel>of();

        var magazines = type == null || type.equalsIgnoreCase("magazine")
                ? magazineService.getAllMagazines()
                : List.<MagazineViewModel>of();

        var stats = includeStats
                ? new LibraryData(
                books.size() + magazines.size(),
                books.size(),
                magazines.size())
                : null;

        return new LibraryViewModel(books, magazines, stats);
    }
}
