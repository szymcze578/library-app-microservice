package org.example.project.Services;

import org.example.project.DataTransferObjects.MagazineDto;
import org.example.project.Domain.Model.Magazine;
import org.example.project.Domain.Repositories.MagazineRepository;
import org.example.project.Exceptions.PublicationAlreadyExistException;
import org.example.project.Exceptions.PublicationNotFoundException;
import org.example.project.Mappers.MagazineMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagazineService {
    private final MagazineRepository magazineRepository;
    private final MagazineMapper magazineMapper;
    public MagazineService(MagazineRepository magazineRepository, MagazineMapper magazineMapper) {
        this.magazineRepository = magazineRepository;
        this.magazineMapper = magazineMapper;
    }

    public List<MagazineDto> getAllMagazines() {
        return magazineRepository.findAll()
                .stream()
                .map(magazineMapper::toMagazineDto)
                .collect(Collectors.toList());
    }

    public Long addMagazine(MagazineDto request) {
        magazineRepository.save(magazineMapper.toMagazine(request));
        return null;
    }

    public void deleteMagazine(Long id) {
        magazineRepository.findById(id).ifPresentOrElse(
                magazine -> magazineRepository.deleteById(magazine.getId()),
                () -> { throw new  PublicationNotFoundException("Magazine with this ID doesn't exist ID::" + id); }
        );
    }

    public MagazineDto updateMagazine(MagazineDto request) {
        var magazine  = magazineRepository.findById(request.id())
                .orElseThrow(
                        () -> new PublicationNotFoundException("Magazine with this ID doesn't exist ID::" + request.id()));
        magazine = mergeMagazine(magazine, request);
        magazineRepository.save(magazine);
        return magazineMapper.toMagazineDto(magazine);
    }

    private Magazine mergeMagazine(Magazine magazine, MagazineDto request) {
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
