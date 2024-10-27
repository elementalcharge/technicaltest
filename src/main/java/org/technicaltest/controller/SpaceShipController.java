package org.technicaltest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technicaltest.dto.SpaceShipDTO;
import org.technicaltest.entity.SpaceShip;
import org.technicaltest.service.SpaceShipService;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceShipController {

    private final SpaceShipService spaceShipService;

    @Autowired
    public SpaceShipController(SpaceShipService spaceShipService) {
        this.spaceShipService = spaceShipService;
    }

    @GetMapping
    public ResponseEntity<Page<SpaceShipDTO>> getAllSpaceShips(Pageable pageable) {
        Page<SpaceShip> spaceShips = spaceShipService.getAllSpaceShips(pageable);
        Page<SpaceShipDTO> spaceShipDTOs = spaceShips.map(spaceShipService::entityToDto);
        return ResponseEntity.ok(spaceShipDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceShipDTO> getSpaceShipById(@PathVariable Integer id) {
        return spaceShipService.getSpaceShipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SpaceShipDTO> createSpaceShip(@RequestBody SpaceShipDTO spaceShipDTO) {
        SpaceShipDTO savedSpaceShip = spaceShipService.saveSpaceShip(spaceShipDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSpaceShip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceShipDTO> updateSpaceShip(@PathVariable Integer id, @RequestBody SpaceShipDTO spaceShipDTO) {
        return spaceShipService.getSpaceShipById(id)
                .map(existingSpaceShip -> {
                    spaceShipDTO.setId(id);
                    SpaceShipDTO updatedSpaceShip = spaceShipService.saveSpaceShip(spaceShipDTO);
                    return ResponseEntity.ok(updatedSpaceShip);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceShip(@PathVariable Integer id) {
        spaceShipService.deleteSpaceShip(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SpaceShipDTO>> searchSpaceShips(
            @RequestParam String name,
            Pageable pageable) {
        Page<SpaceShipDTO> spaceShips = spaceShipService.findSpaceShipsByNameContaining(name, pageable);
        return ResponseEntity.ok(spaceShips);
    }
}