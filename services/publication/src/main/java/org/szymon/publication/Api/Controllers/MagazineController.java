package org.szymon.publication.Api.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.szymon.publication.Api.Models.CreateMagazineRequest;
import org.szymon.publication.Api.Models.MagazineResponse;
import org.szymon.publication.Services.MagazineService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/magazines")
public class MagazineController {

    private final MagazineService magazineService;

    @PostMapping()
    public ResponseEntity<MagazineResponse> createMagazine(@RequestBody @Valid CreateMagazineRequest magazineRequest) {
        MagazineResponse magazineResponse = magazineService.createMagazine(magazineRequest);
        return ResponseEntity.ok(magazineResponse);
    }
}
