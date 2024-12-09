package beforgts.ec.api_notta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService { // classe para o envio do email para ativação da conta do usuário.
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String token) {
        String subject = "Ativação da Conta";
        String text =
                "Clique no link para ativar sua conta: http://localhost:8080/api/v1/auth/activate?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }
}
