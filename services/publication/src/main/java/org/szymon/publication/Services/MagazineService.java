package org.szymon.publication.Services;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.szymon.publication.Api.Models.CreateMagazineRequest;
import org.szymon.publication.Api.Models.MagazineResponse;
import org.szymon.publication.Domain.Model.Magazine;
import org.szymon.publication.Domain.Repositories.MagazineRepository;
import org.szymon.publication.Exceptions.DuplicateIsbnException;

@Service
@AllArgsConstructor
public class MagazineService {

    private final MagazineRepository magazineRepository;

    public MagazineResponse createMagazine(CreateMagazineRequest request){
        if (magazineRepository.existsByIssn(request.issn())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Magazine already exists");
        }

        Magazine magazine = Magazine.builder()
                .title(request.title().trim())
                .publicationYear(request.publicationYear())
                .publisher(request.publisher().trim())
                .language(request.language().trim().toLowerCase())
                .category(request.category() == null ? null : request.category().trim())
                .issueDate(request.issueDate())
                .issueNumber(request.issueNumber())
                .periodicity(request.periodicity())
                .issn(request.issn())
                .description(request.description()
        ).build();

        try {
            Magazine saved = magazineRepository.save(magazine);
            return MagazineResponse.from(saved, 0);
        }
        catch (DataIntegrityViolationException e){
            throw new DuplicateIsbnException(request.issn());
        }
    }
}
