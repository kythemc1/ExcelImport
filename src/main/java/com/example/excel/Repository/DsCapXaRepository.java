package com.example.excel.Repository;

import com.example.excel.Entity.DsCapXa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DsCapXaRepository extends JpaRepository<DsCapXa,Long> {
}
