package br.com.lucasmacas.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

  
  /**
   * 
   * ID
   * Usuario (ID_USUARIO)
   * Descrição
   * Titulo
   * Data de criação
   * Data de conclusão
   * Prioridade
   */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {


  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String description;

  @Column(length = 50)
  private String title;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String priority;
  
  private UUID idUser;

  @CreationTimestamp
  private LocalDateTime createdAt;
  
  public void setTitle(String title) throws IllegalArgumentException {
    if (title.length() > 50) {
      throw new IllegalArgumentException("Title must be less than 50 characters");
    }
    this.title = title;
  }
}
