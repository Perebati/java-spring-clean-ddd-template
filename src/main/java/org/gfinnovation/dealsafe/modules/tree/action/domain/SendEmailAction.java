package org.gfinnovation.dealsafe.modules.tree.action.domain;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class SendEmailAction
 * @since 16/01/2025
 */

public class SendEmailAction extends Action {
    private final String to;
    private final String subject;
    private final String body;

    public SendEmailAction(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public void execute() {
        System.out.println("Enviando e-mail para " + to + " com o assunto '" + subject + "'.");
    }

}
