package com.example.excel.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Xa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DsCapXa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long WardId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "DistrictId" )
    private DsCapHuyen DistrictId;
}
