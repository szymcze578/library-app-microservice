package org.example.project.Mappers;

import org.example.project.DataTransferObjects.MagazineViewModel;
import org.example.project.Domain.Model.Magazine;
import org.springframework.stereotype.Service;

@Service
public class MagazineMapper {
    public Magazine map(MagazineViewModel request) {
        return Magazine.builder()
                .title(request.title())
                .year(request.year())
                .publisher(request.publisher())
                .monthDay(request.monthDay())
                .language(request.language())
                .build();
    }

    public MagazineViewModel map(Magazine magazine) {
        return new MagazineViewModel(
                magazine.getId(),
                magazine.getTitle(),
                magazine.getPublisher(),
                magazine.getYear(),
                magazine.getMonthDay(),
                magazine.getLanguage()
        );
    }
}
