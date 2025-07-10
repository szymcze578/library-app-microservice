package org.example.project.Interfaces;

import org.example.project.DataTransferObjects.Library.LibraryViewModel;

public interface ILibraryService {

    LibraryViewModel getAllPublications(String type, boolean includeStats);
}
