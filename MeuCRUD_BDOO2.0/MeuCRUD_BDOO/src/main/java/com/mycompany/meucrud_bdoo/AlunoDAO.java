package com.mycompany.meucrud_bdoo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Classe DAO para operações CRUD com Aluno
 */
public class AlunoDAO {
    
    private static final String DB_URL = "objectdb:database.odb";
    private static EntityManagerFactory emf;
    
    // Singleton para EntityManagerFactory
    private static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(DB_URL);
        }
        return emf;
    }
    
    // Fechar EntityManagerFactory
    public static void fecharFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    // Criar um novo aluno
    public void salvarAluno(Aluno aluno) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno salvo com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // Buscar aluno por ID
    public Aluno buscarAlunoPorId(Long id) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close();
        }
    }
    
    // Buscar aluno por matrícula
    public Aluno buscarAlunoPorMatricula(String matricula) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        try {
            TypedQuery<Aluno> query = em.createQuery(
                "SELECT a FROM Aluno a WHERE a.matricula = :matricula", Aluno.class);
            query.setParameter("matricula", matricula);
            List<Aluno> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    // Listar todos os alunos
    public List<Aluno> listarTodosAlunos() {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        try {
            TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Atualizar aluno
    public void atualizarAluno(Aluno aluno) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // Deletar aluno
    public void deletarAluno(Long id) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        try {
            em.getTransaction().begin();
            Aluno aluno = em.find(Aluno.class, id);
            if (aluno != null) {
                em.remove(aluno);
                em.getTransaction().commit();
                System.out.println("Aluno removido com sucesso!");
            } else {
                System.out.println("Aluno não encontrado!");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao remover aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // Adicionar histórico a um aluno
    public void adicionarHistoricoAoAluno(Long alunoId, HistoricoEscolar historico) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        
        try {
            em.getTransaction().begin();
            Aluno aluno = em.find(Aluno.class, alunoId);
            if (aluno != null) {
                aluno.adicionarHistorico(historico);
                em.merge(aluno);
                em.getTransaction().commit();
                System.out.println("Histórico adicionado com sucesso!");
            } else {
                System.out.println("Aluno não encontrado!");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao adicionar histórico: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
