package com.green.school.green.repository;

import com.green.school.green.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SiswaRepository extends JpaRepository<Siswa, Integer> {
    Optional<Siswa> findById(int id);
    Optional<Siswa> findByNik(String nik);
}
