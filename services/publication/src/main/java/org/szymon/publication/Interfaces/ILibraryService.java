package org.szymon.publication.Interfaces;

import org.szymon.publication.DataTransferObjects.Library.LibraryViewModel;

public interface ILibraryService {

    LibraryViewModel getAllPublications(String type, boolean includeStats);
}
