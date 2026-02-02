package com.example.dto;

public class PersonelDTO {

    private Long id;
    private String ad;
    private String soyad;
    private String tcNo;
    private Long hastaSayisi;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }

    public String getTcNo() { return tcNo; }
    public void setTcNo(String tcNo) { this.tcNo = tcNo; }

    public Long getHastaSayisi() { return hastaSayisi; }
    public void setHastaSayisi(Long hastaSayisi) {
        this.hastaSayisi = hastaSayisi;
    }
}
