package vn.edu.lab4.model;

import jakarta.validation.constraints.*;

public class TinTuc {
    private int matt;

    @NotBlank @Size(max = 150)
    private String tieuDe;

    @NotBlank @Size(max = 255)
    private String noiDungTT;

    @NotBlank
    @Pattern(regexp="^http://.*$", message="Liên kết phải bắt đầu bằng http://")
    private String lienKet;

    @Min(1)
    private int madm;

    public int getMatt(){ return matt; }
    public void setMatt(int matt){ this.matt = matt; }
    public String getTieuDe(){ return tieuDe; }
    public void setTieuDe(String tieuDe){ this.tieuDe = tieuDe; }
    public String getNoiDungTT(){ return noiDungTT; }
    public void setNoiDungTT(String noiDungTT){ this.noiDungTT = noiDungTT; }
    public String getLienKet(){ return lienKet; }
    public void setLienKet(String lienKet){ this.lienKet = lienKet; }
    public int getMadm(){ return madm; }
    public void setMadm(int madm){ this.madm = madm; }
}
