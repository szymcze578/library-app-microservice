package org.example.project.DataTransferObjects.Library;

import org.example.project.DataTransferObjects.BookViewModel;
import org.example.project.DataTransferObjects.MagazineViewModel;

import java.util.List;

public record LibraryViewModel(
        List<BookViewModel> books,
        List<MagazineViewModel> magazines,
        LibraryData stats
) {}


