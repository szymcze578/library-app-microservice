package org.example.project.Interfaces;

import org.example.project.DataTransferObjects.MagazineViewModel;

import java.util.List;

public interface IMagazineService {

    List<MagazineViewModel> getAllMagazines();
    Long addMagazine(MagazineViewModel request);

    void deleteMagazine(Long id);

    MagazineViewModel updateMagazine(MagazineViewModel request);
}
