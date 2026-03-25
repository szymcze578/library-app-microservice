package org.szymon.publication.Services;


import org.springframework.stereotype.Service;
import org.szymon.publication.DataTransferObjects.MagazineViewModel;
import org.szymon.publication.Domain.Model.Magazine;
import org.szymon.publication.Domain.Repositories.MagazineRepository;
import org.szymon.publication.Exceptions.PublicationNotFoundException;
import org.szymon.publication.Interfaces.IMagazineService;
import org.szymon.publication.Mappers.MagazineMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagazineService implements IMagazineService {

    private final MagazineRepository magazineRepository;
    private final MagazineMapper magazineMapper;

    public MagazineService(MagazineRepository magazineRepository, MagazineMapper magazineMapper) {
        this.magazineRepository = magazineRepository;
        this.magazineMapper = magazineMapper;
    }

    public List<MagazineViewModel> getAllMagazines() {
        return magazineRepository.findAll()
                .stream()
                .map(magazineMapper::map)
                .collect(Collectors.toList());
    }

    public Long addMagazine(MagazineViewModel request) {
        magazineRepository.save(magazineMapper.map(request));
        return null;
    }

    public void deleteMagazine(Long id) {
        magazineRepository.findById(id).ifPresentOrElse(
                magazine -> magazineRepository.deleteById(magazine.getId()),
                () -> { throw new PublicationNotFoundException("Magazine with this ID doesn't exist ID::" + id); }
        );
    }

    public MagazineViewModel updateMagazine(MagazineViewModel request) {
        var magazine  = magazineRepository.findById(request.id())
                .orElseThrow(
                        () -> new PublicationNotFoundException("Magazine with this ID doesn't exist ID::" + request.id()));
        magazine = mergeMagazine(magazine, request);
        magazineRepository.save(magazine);
        return magazineMapper.map(magazine);
    }

    private Magazine mergeMagazine(Magazine magazine, MagazineViewModel request) {
        if(!request.title().isBlank()){
            magazine.setTitle(request.title());
        }
        if(!request.publisher().isBlank()){
            magazine.setPublisher(request.publisher());
        }
        if(!request.year().isBlank()){
            magazine.setYear(request.year());
        }
        if(!request.monthDay().isBlank()){
            magazine.setMonthDay(request.monthDay());
        }
        if(!request.language().isBlank()){
            magazine.setLanguage(request.language());
        }
        return magazine;
    }
}
