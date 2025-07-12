package org.szymon.publicationservice.Interfaces;

import org.szymon.publicationservice.DataTransferObjects.Library.LibraryViewModel;

public interface ILibraryService {

    LibraryViewModel getAllPublications(String type, boolean includeStats);
}
