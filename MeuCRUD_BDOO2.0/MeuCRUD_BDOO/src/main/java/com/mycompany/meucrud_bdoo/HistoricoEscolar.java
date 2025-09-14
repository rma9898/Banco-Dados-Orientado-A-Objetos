package com.mycompany.meucrud_bdoo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidade que representa o histórico escolar de um aluno
 */
@Entity
public class HistoricoEscolar {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String disciplina;
    private String semestre;
    private Double nota;
    private String situacao; // "Aprovado", "Reprovado", "Cursando"
    
    // Relacionamento muitos-para-um com Aluno
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    
    // Construtor padrão
    public HistoricoEscolar() {}
    
    // Construtor com parâmetros
    public HistoricoEscolar(String disciplina, String semestre, Double nota, String situacao) {
        this.disciplina = disciplina;
        this.semestre = semestre;
        this.nota = nota;
        this.situacao = situacao;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDisciplina() {
        return disciplina;
    }
    
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
    
    public String getSemestre() {
        return semestre;
    }
    
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    public Double getNota() {
        return nota;
    }
    
    public void setNota(Double nota) {
        this.nota = nota;
    }
    
    public String getSituacao() {
        return situacao;
    }
    
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    @Override
    public String toString() {
        return "HistoricoEscolar{" +
                "id=" + id +
                ", disciplina='" + disciplina + '\'' +
                ", semestre='" + semestre + '\'' +
                ", nota=" + nota +
                ", situacao='" + situacao + '\'' +
                '}';
    }
}
