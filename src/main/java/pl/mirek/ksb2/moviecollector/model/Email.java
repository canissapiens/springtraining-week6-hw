package pl.mirek.ksb2.moviecollector.model;

public class Email {

    private String subject;
    private String content;
    private String emailAddress;

    private Email(EmailBuilder builder) {
        this.subject = builder.getSubject();
        this.content = builder.getContent();
        this.emailAddress = builder.getEmailAddress();
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", recipientEmail='" + emailAddress + '\'' +
                '}';
    }

    public static class EmailBuilder {
        private String subject;
        private String content;
        private String emailAddress;

        public EmailBuilder() {
        }

        public String getSubject() {
            return subject;
        }

        public String getContent() {
            return content;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public EmailBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public EmailBuilder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }

}
