package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "corso")
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_corso", nullable = false)
    private String nomeCorso;

    @Column(nullable = false)
    private int oreCorso;

    @Column(nullable = false)
    private int annoAccademico;

    @ManyToOne
    @JoinColumn(name = "id_docente", referencedColumnName = "id")
    private Docente docente;

    public Corso(){}
    public Corso(String nomeCorso, int oreCorso, int annoAccademico, Docente docente){
        this.nomeCorso = nomeCorso;
        this.oreCorso = oreCorso;
        this.annoAccademico = annoAccademico;
        this.docente = docente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public int getOreCorso() {
        return oreCorso;
    }

    public void setOreCorso(int oreCorso) {
        this.oreCorso = oreCorso;
    }

    public int getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(int annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}
