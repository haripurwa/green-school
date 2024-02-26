package com.green.school.green.repository;

import com.green.school.green.model.Dosen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DosenRepository extends JpaRepository<Dosen, UUID> {
    Optional<Dosen> findById(UUID id);
    Optional<Dosen> findByNik(String nik);
}
