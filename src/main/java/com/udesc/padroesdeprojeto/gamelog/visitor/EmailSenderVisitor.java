package com.udesc.padroesdeprojeto.gamelog.visitor;

import com.udesc.padroesdeprojeto.gamelog.model.DetailedReview;
import com.udesc.padroesdeprojeto.gamelog.model.Review;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.service.JavaMailSenderService;

public class EmailSenderVisitor implements ReviewVisitor{
    private final JavaMailSenderService mailSenderService;
    private final User user;

    public EmailSenderVisitor(JavaMailSenderService mailSenderService, User user){
        this.mailSenderService = mailSenderService;
        this.user = user;
    }

    @Override
    public void visit(Review review) {
        String body = String.format("Olá %s,\n\n" +
                        "Essa é uma de suas reviews.\n" +
                        "Detalhes da Avaliação:\n" +
                        "Título: %s\n" +
                        "Avaliação: %s\n" +
                        "Comentário: %s\n" +
                        "Não se esqueça de avaliá-la!",
                user.getUsername(),
                review.getTitle(),
                review.getRating(),
                review.getComment());
        mailSenderService.sendEmail(user.getEmail(), "Review simples", body);
    }

    @Override
    public void visit(DetailedReview detailedReview) {
        String pros = String.join(", ", detailedReview.getPros());
        String cons = String.join(", ", detailedReview.getCons());

        String body = String.format("Olá %s,\n\n" +
                        "Essa é uma de suas reviews detalhadas.\n" +
                        "Detalhes da Avaliação:\n" +
                        "Título: %s\n" +
                        "Avaliação: %s\n" +
                        "Comentário: %s\n" +
                        "Prós: %s\n" +
                        "Contras: %s\n" +
                        "Não se esqueça de avaliá-la!",
                user.getUsername(),
                detailedReview.getTitle(),
                detailedReview.getRating(),
                detailedReview.getComment(),
                pros,
                cons);
        mailSenderService.sendEmail(user.getEmail(), "Review detalhada", body);
    }
}
