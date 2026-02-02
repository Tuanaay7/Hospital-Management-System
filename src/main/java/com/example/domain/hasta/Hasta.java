package com.example.domain.hasta;

import com.example.domain.personel.Personel;
import jakarta.persistence.*;

@Entity
@Table(name = "HASTATUANA", schema = "STAJDEMO")
public class Hasta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hasta_seq"
    )
   @SequenceGenerator(
        name = "hasta_seq",
        sequenceName = "HASTA_TUANA_SEQ",
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

    @ManyToOne
    @JoinColumn(name = "PERSONEL_ID", nullable = false)
    private Personel personel;

    public Hasta() {}

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

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }
}
