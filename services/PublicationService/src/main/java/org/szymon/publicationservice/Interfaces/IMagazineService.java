package org.szymon.publicationservice.Interfaces;


import org.szymon.publicationservice.DataTransferObjects.MagazineViewModel;

import java.util.List;

public interface IMagazineService {

    List<MagazineViewModel> getAllMagazines();
    Long addMagazine(MagazineViewModel request);

    void deleteMagazine(Long id);

    MagazineViewModel updateMagazine(MagazineViewModel request);
}
