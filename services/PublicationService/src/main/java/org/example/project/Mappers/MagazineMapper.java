package org.example.project.Mappers;

import org.example.project.DataTransferObjects.MagazineDto;
import org.example.project.Domain.Model.Magazine;
import org.springframework.stereotype.Service;

@Service
public class MagazineMapper {
    public MagazineDto toMagazineDto(Magazine magazine) {
        return new MagazineDto(
                magazine.getId(),
                magazine.getTitle(),
                magazine.getPublisher(),
                magazine.getYear(),
                magazine.getMonthDay(),
                magazine.getLanguage()
        );
    }

    public Magazine toMagazine(MagazineDto request) {
        return Magazine.builder()
                .id(request.id())
                .title(request.title())
                .year(request.year())
                .publisher(request.publisher())
                .monthDay(request.monthDay())
                .language(request.language())
                .build();
    }
}
