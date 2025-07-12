package org.szymon.publicationservice.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.szymon.publicationservice.Domain.Model.Book;
import org.szymon.publicationservice.Domain.Model.Magazine;
import org.szymon.publicationservice.Model.PublicationDTO;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure explicit mapping for Book
        modelMapper.typeMap(PublicationDTO.class, Book.class).addMappings(mapper -> {
            mapper.map(PublicationDTO::getAuthor, Book::setAuthor);
            mapper.map(PublicationDTO::getPages, Book::setPages);
            mapper.map(PublicationDTO::getIsbn, Book::setIsbn);
        });

        modelMapper.typeMap(PublicationDTO.class, Magazine.class).addMappings(mapper -> {
            mapper.map(PublicationDTO::getMonthDay, Magazine::setMonthDay);
            mapper.map(PublicationDTO::getLanguage, Magazine::setLanguage);
        });

        return modelMapper;
    }
}
