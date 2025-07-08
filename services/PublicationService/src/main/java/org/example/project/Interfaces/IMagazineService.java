package org.example.project.Interfaces;

import org.example.project.DataTransferObjects.MagazineDto;

import java.util.List;

public interface IMagazineService {

    List<MagazineDto> getAllMagazines();
    Long addMagazine(MagazineDto request);

    void deleteMagazine(Long id);

    MagazineDto updateMagazine(MagazineDto request);
}
