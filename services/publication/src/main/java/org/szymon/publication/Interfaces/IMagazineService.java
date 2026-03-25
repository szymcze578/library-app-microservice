package org.szymon.publication.Interfaces;


import org.szymon.publication.DataTransferObjects.MagazineViewModel;

import java.util.List;

public interface IMagazineService {

    List<MagazineViewModel> getAllMagazines();
    Long addMagazine(MagazineViewModel request);

    void deleteMagazine(Long id);

    MagazineViewModel updateMagazine(MagazineViewModel request);
}
