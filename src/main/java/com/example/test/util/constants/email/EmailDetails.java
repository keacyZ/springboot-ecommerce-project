package com.example.test.util.constants.email;



public class EmailDetails {

    private String recipient;
    private String messageBody;
    private String subject;

    public EmailDetails(String recipient, String messageBody, String subject) {
        this.recipient = recipient;
        this.messageBody = messageBody;
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getSubject() {
        return subject;
    }
}
