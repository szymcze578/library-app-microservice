package org.szymon.publication.DataTransferObjects.Library;

import org.szymon.publication.DataTransferObjects.BookViewModel;
import org.szymon.publication.DataTransferObjects.MagazineViewModel;
import java.util.List;

public record LibraryViewModel(
        List<BookViewModel> books,
        List<MagazineViewModel> magazines,
        LibraryData stats
) {}


