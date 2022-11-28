package com.example.excel.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Huyen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DsCapHuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long DistrictId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "CityId")
    private DsCapTinh CityId;

}
