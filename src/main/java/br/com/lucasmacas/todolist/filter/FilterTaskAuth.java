package br.com.lucasmacas.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.lucasmacas.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

      var serveletPath = request.getServletPath();

      if (serveletPath.startsWith("/tasks/")) {
         // pegar auth
      var authorization = request.getHeader("Authorization");
      

      var authEncoded = authorization.substring("Basic".length()).trim();

      byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

      var authString = new String(authDecoded);

      
      // [username, password]
      String[] credentials = authString.split(":");
      String username = credentials[0];
      String password = credentials[1];
      System.out.println("Authorization");
      System.out.println("Username: " + username);
      System.out.println("Password: " + password);

      // validar user
      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401); 
      }else{
        // validar pass
          var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
          if (passwordVerify.verified) {
            // seguir viagem
            request.setAttribute("idUser", user.getId());
            filterChain.doFilter(request, response);

          } else {
            response.sendError(401);
          }
      }
        
      }else{
        filterChain.doFilter(request, response);
      }
      
     

  }

  
    
}
  

