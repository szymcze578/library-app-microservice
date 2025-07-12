package org.szymon.publicationservice.DataTransferObjects.Library;

import org.szymon.publicationservice.DataTransferObjects.BookViewModel;
import org.szymon.publicationservice.DataTransferObjects.MagazineViewModel;
import java.util.List;

public record LibraryViewModel(
        List<BookViewModel> books,
        List<MagazineViewModel> magazines,
        LibraryData stats
) {}


