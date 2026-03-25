package org.szymon.publication.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymon.publication.DataTransferObjects.MagazineViewModel;
import org.szymon.publication.Services.MagazineService;

import java.util.List;

@RequestMapping("/api/v1/magazines")
@RestController
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping("/getMagazines")
    public ResponseEntity<List<MagazineViewModel>> getAllMagazines(){
        return ResponseEntity.ok(magazineService.getAllMagazines());
    }

    @PostMapping("/addMagazine")
    public ResponseEntity<Long> addMagazine(@RequestBody @Valid MagazineViewModel request){
        return ResponseEntity.ok(magazineService.addMagazine(request));
    }

    @DeleteMapping("/deleteMagazine")
    public ResponseEntity<?> deleteMagazine(@RequestParam("magazine-id") Long id){
        magazineService.deleteMagazine(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("updateMagazine")
    public ResponseEntity<MagazineViewModel> updateMagazine(@RequestBody @Valid MagazineViewModel request){
        return ResponseEntity.ok(magazineService.updateMagazine(request));
    }
}
