package com.example.domain.personel;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONELTUANA", schema = "STAJDEMO")
public class Personel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "personel_seq"
    )
    @SequenceGenerator(
        name = "personel_seq",
        sequenceName = "PERSONEL_TUANA_SEQ",
        allocationSize = 1
    )
    
    @Column(name = "ID")
    private Long id;

    @Column(name = "AD", nullable = false)
    private String ad;

    @Column(name = "SOYAD", nullable = false)
    private String soyad;

    @Column(name = "TCNO", nullable = false, unique = true)
    private String tcNo;

    public Personel() {}

    public Long getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }
}
