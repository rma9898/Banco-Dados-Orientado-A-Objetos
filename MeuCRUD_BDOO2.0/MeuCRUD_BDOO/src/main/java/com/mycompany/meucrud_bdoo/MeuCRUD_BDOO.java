/*
 * Sistema CRUD simples para histórico de alunos usando ObjectDB
 */

package com.mycompany.meucrud_bdoo;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal com menu interativo para CRUD de alunos
 * @author koba
 */
public class MeuCRUD_BDOO {
    
    private static AlunoDAO alunoDAO = new AlunoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sistema de Histórico Escolar ===");
        
        while (true) {
            mostrarMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    buscarAluno();
                    break;
                case 4:
                    atualizarAluno();
                    break;
                case 5:
                    removerAluno();
                    break;
                case 6:
                    adicionarHistorico();
                    break;
                case 7:
                    visualizarHistorico();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    AlunoDAO.fecharFactory(); // Fecha a factory do banco
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
            
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Listar Todos os Alunos");
        System.out.println("3. Buscar Aluno por Matrícula");
        System.out.println("4. Atualizar Dados do Aluno");
        System.out.println("5. Remover Aluno");
        System.out.println("6. Adicionar Histórico");
        System.out.println("7. Visualizar Histórico do Aluno");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void cadastrarAluno() {
        System.out.println("\n=== CADASTRAR ALUNO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        
        Aluno aluno = new Aluno(nome, matricula, email, curso);
        alunoDAO.salvarAluno(aluno);
    }
    
    private static void listarAlunos() {
        System.out.println("\n=== LISTA DE ALUNOS ===");
        List<Aluno> alunos = alunoDAO.listarTodosAlunos();
        
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : alunos) {
                System.out.println("ID: " + aluno.getId() + 
                                 " | Nome: " + aluno.getNome() + 
                                 " | Matrícula: " + aluno.getMatricula() + 
                                 " | Curso: " + aluno.getCurso());
            }
        }
    }
    
    private static void buscarAluno() {
        System.out.println("\n=== BUSCAR ALUNO ===");
        System.out.print("Digite a matrícula: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = alunoDAO.buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno encontrado:");
            System.out.println(aluno);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }
    
    private static void atualizarAluno() {
        System.out.println("\n=== ATUALIZAR ALUNO ===");
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = alunoDAO.buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno encontrado: " + aluno.getNome());
            
            System.out.print("Novo nome (atual: " + aluno.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.trim().isEmpty()) {
                aluno.setNome(nome);
            }
            
            System.out.print("Novo email (atual: " + aluno.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.trim().isEmpty()) {
                aluno.setEmail(email);
            }
            
            System.out.print("Novo curso (atual: " + aluno.getCurso() + "): ");
            String curso = scanner.nextLine();
            if (!curso.trim().isEmpty()) {
                aluno.setCurso(curso);
            }
            
            alunoDAO.atualizarAluno(aluno);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }
    
    private static void removerAluno() {
        System.out.println("\n=== REMOVER ALUNO ===");
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = alunoDAO.buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno encontrado: " + aluno.getNome());
            System.out.print("Confirma a remoção? (s/n): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("s")) {
                alunoDAO.deletarAluno(aluno.getId());
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }
    
    private static void adicionarHistorico() {
        System.out.println("\n=== ADICIONAR HISTÓRICO ===");
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = alunoDAO.buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno: " + aluno.getNome());
            
            System.out.print("Disciplina: ");
            String disciplina = scanner.nextLine();
            
            System.out.print("Semestre (ex: 2024.1): ");
            String semestre = scanner.nextLine();
            
            System.out.print("Nota: ");
            Double nota = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Situação (Aprovado/Reprovado/Cursando): ");
            String situacao = scanner.nextLine();
            
            HistoricoEscolar historico = new HistoricoEscolar(disciplina, semestre, nota, situacao);
            alunoDAO.adicionarHistoricoAoAluno(aluno.getId(), historico);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }
    
    private static void visualizarHistorico() {
        System.out.println("\n=== HISTÓRICO DO ALUNO ===");
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = alunoDAO.buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Histórico de: " + aluno.getNome());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println();
            
            List<HistoricoEscolar> historicos = aluno.getHistoricos();
            if (historicos.isEmpty()) {
                System.out.println("Nenhum histórico cadastrado.");
            } else {
                System.out.println("DISCIPLINA\t\tSEMESTRE\tNOTA\tSITUAÇÃO");
                System.out.println("------------------------------------------------------");
                for (HistoricoEscolar historico : historicos) {
                    System.out.printf("%-20s\t%s\t\t%.1f\t%s\n", 
                        historico.getDisciplina(),
                        historico.getSemestre(),
                        historico.getNota(),
                        historico.getSituacao());
                }
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }
}
