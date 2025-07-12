package org.szymon.publicationservice.Services;


import org.springframework.stereotype.Service;
import org.szymon.publicationservice.DataTransferObjects.BookViewModel;
import org.szymon.publicationservice.DataTransferObjects.Library.LibraryData;
import org.szymon.publicationservice.DataTransferObjects.Library.LibraryViewModel;
import org.szymon.publicationservice.DataTransferObjects.MagazineViewModel;
import org.szymon.publicationservice.Interfaces.IBookService;
import org.szymon.publicationservice.Interfaces.ILibraryService;
import org.szymon.publicationservice.Interfaces.IMagazineService;

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
