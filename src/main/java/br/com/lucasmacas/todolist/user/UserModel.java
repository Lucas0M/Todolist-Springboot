package br.com.lucasmacas.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Gerar os métodos getters, setters, toString, equals e hashCode automaticamente
@Entity(name = "tb_users") // Indica que esta classe é uma entidade do banco de dados
public class UserModel {
  
  @Id // Indica que este campo é a chave primária da tabela
  @GeneratedValue(generator = "UUID") // Indica que o valor do campo será gerado automaticamente como um UUID
  private UUID id;

  @Column(unique = true) // Indica que este campo deve ser único e não pode ser nulo
  private String username;
  private String name;
  private String password;
  
  @CreationTimestamp // Indica que este campo deve ser preenchido automaticamente com a data e hora de criação do registro
  private LocalDateTime createdAt;
}
