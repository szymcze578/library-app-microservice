package org.example.project.Services;

import org.example.project.DataTransferObjects.BookViewModel;
import org.example.project.DataTransferObjects.Library.LibraryData;
import org.example.project.DataTransferObjects.Library.LibraryViewModel;
import org.example.project.DataTransferObjects.MagazineViewModel;
import org.example.project.Interfaces.IBookService;
import org.example.project.Interfaces.ILibraryService;
import org.example.project.Interfaces.IMagazineService;
import org.springframework.stereotype.Service;

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
