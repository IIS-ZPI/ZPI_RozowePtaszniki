package app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product
{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name="nazwa", nullable = false)
    private String nazwa;

    @Column(name="cena", nullable = false)
    private double cena;

    @Column(name="kategoria", nullable = false)
    private String kategoria;

    Product(int id, String nazwa, double cena, String kategoria)
    {
        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
        this.kategoria = kategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}