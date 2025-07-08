package org.example.project.Controllers;

import jakarta.validation.Valid;
import org.example.project.DataTransferObjects.MagazineDto;
import org.example.project.Domain.Model.Magazine;
import org.example.project.Services.MagazineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/magazines")
@RestController
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping("/getMagazines")
    public ResponseEntity<List<MagazineDto>> getAllMagazines(){
        return ResponseEntity.ok(magazineService.getAllMagazines());
    }

    @PostMapping("/addMagazine")
    public ResponseEntity<Long> addMagazine(@RequestBody @Valid MagazineDto request){
        return ResponseEntity.ok(magazineService.addMagazine(request));
    }

    @DeleteMapping("/deleteMagazine")
    public ResponseEntity<?> deleteMagazine(@RequestParam("magazine-id") Long id){
        magazineService.deleteMagazine(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("updateMagazine")
    public ResponseEntity<MagazineDto> updateMagazine(@RequestBody @Valid MagazineDto request){
        return ResponseEntity.ok(magazineService.updateMagazine(request));
    }
}
