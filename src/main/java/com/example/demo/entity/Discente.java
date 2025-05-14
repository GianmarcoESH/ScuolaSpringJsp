package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "discente")
public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_Discente", nullable = false)
    private String nome;

    @Column(name = "cognome_Discente", nullable = false)
    private String cognome;

    @Column(nullable = false)
    private Date dataDiNascita;

    @Column
    private String cittaDiResidenza;

    @Column(nullable = false)
    private int voto;

    public Discente(){}
    public Discente(String nome, String cognome, Date dataDiNascita, String cittaDiResidenza, int voto){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.cittaDiResidenza = cittaDiResidenza;
        this.voto = voto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getCittaDiResidenza() {
        return cittaDiResidenza;
    }

    public void setCittaDiResidenza(String cittaDiResidenza) {
        this.cittaDiResidenza = cittaDiResidenza;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}
