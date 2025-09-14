package com.mycompany.meucrud_bdoo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade que representa um aluno
 */
@Entity
public class Aluno {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String nome;
    private String matricula;
    private String email;
    private String curso;
    
    // Relacionamento um-para-muitos com HistoricoEscolar
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HistoricoEscolar> historicos = new ArrayList<>();
    
    // Construtor padrão
    public Aluno() {}
    
    // Construtor com parâmetros
    public Aluno(String nome, String matricula, String email, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.curso = curso;
    }
    
    // Getters e Setters
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
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCurso() {
        return curso;
    }
    
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public List<HistoricoEscolar> getHistoricos() {
        return historicos;
    }
    
    public void setHistoricos(List<HistoricoEscolar> historicos) {
        this.historicos = historicos;
    }
    
    // Método para adicionar histórico
    public void adicionarHistorico(HistoricoEscolar historico) {
        historicos.add(historico);
        historico.setAluno(this);
    }
    
    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", email='" + email + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
