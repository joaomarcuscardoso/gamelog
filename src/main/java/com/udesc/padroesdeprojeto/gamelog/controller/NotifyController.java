package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.observer.ServerEvent;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotifyController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailSender mailSender;

    @GetMapping("/update/off/{token}")
    public ResponseEntity<Object> serverUpdate(@PathVariable String token) {
        User user = userRepository.findByToken(token).orElse(null);
        if (user == null)
            return ResponseEntity.badRequest().body("Você não tem permissão.");


        System.out.println("ROLE: " + user.getRole());
        if (!user.getRole().equals("ADMIN"))
            return ResponseEntity.badRequest().body("Você não tem permissão.");

        ServerEvent gameEvent = ServerEvent.getInstance();
        gameEvent.notifyObservers(mailSender, "Servidor em Manutenção", "O servidor está passado por um momento de atualização. Agradecemos a compreensão.");
        return ResponseEntity.ok("Todos os usuários foram notificados");
    }

}
