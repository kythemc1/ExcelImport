package com.example.excel.Repository;

import com.example.excel.Entity.DsCapHuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DsCapHuyenRepository extends JpaRepository<DsCapHuyen,Long> {

}
