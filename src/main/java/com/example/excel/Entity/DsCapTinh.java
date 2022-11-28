package com.example.excel.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DsCapTinh" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DsCapTinh {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CityId;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Code")
    private String Code;

}
