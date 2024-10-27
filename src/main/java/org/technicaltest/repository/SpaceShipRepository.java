package org.technicaltest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.technicaltest.entity.SpaceShip;

@Repository
public interface SpaceShipRepository extends JpaRepository<SpaceShip, Integer>, ISpaceShipRepository {
    Page<SpaceShip> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
