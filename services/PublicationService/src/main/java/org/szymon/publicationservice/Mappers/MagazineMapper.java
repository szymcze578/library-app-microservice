package org.szymon.publicationservice.Mappers;


import org.springframework.stereotype.Service;
import org.szymon.publicationservice.DataTransferObjects.MagazineViewModel;
import org.szymon.publicationservice.Domain.Model.Magazine;

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
