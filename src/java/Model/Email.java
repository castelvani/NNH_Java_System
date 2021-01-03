/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    private static Session sessao;

    public void EmailReserva(String email, Date dt_entrada, Date dt_saida) {
        Properties props = new Properties();
        //Conexão com servidor Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String meuEmail = "hotelnnh@gmail.com";
        String minhaSenha = "nnh2020!";

        try {
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(meuEmail, minhaSenha);
                }
            };
            sessao = Session.getInstance(props, auth);
        } catch (Exception ex) {
            System.out.println("Erro" + ex.getMessage());
        }

        try {
            Message message = new MimeMessage(sessao);
            //Quem envia
            message.setFrom(new InternetAddress(meuEmail));

            //Quem recebe
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("NNH - Reserva realizada com sucesso!");//Assunto
            message.setText("Olá, sua reserva foi realizada com sucesso!\n\n"
                    + "Data de entrada: " + dt_entrada
                    + "\nData de saída: " + dt_saida);

            //Método para enviar a mensagem criada
            Transport.send(message);

            System.out.println("Enviada");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void EmailCadastro(String email) {
        Properties props = new Properties();
        //Conexão com servidor Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String meuEmail = "hotelnnh@gmail.com";
        String minhaSenha = "nnh2020!";

        try {
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(meuEmail, minhaSenha);
                }
            };
            sessao = Session.getInstance(props, auth);
        } catch (Exception ex) {
            System.out.println("Erro" + ex.getMessage());
        }

        try {
            Message message = new MimeMessage(sessao);
            //Quem envia
            message.setFrom(new InternetAddress(meuEmail));

            //Quem recebe
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("NNH - Cadastro realizado com sucesso!");//Assunto
            message.setText("Olá, seu cadastro no NNH foi realizado com sucesso!");

            //Método para enviar a mensagem criada
            Transport.send(message);

            System.out.println("Enviada");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void EmailDesconto(List<Usuario> UsuarioEmail, int desconto) {
        Properties props = new Properties();
        //Conexão com servidor Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String meuEmail = "hotelnnh@gmail.com";
        String minhaSenha = "nnh2020!";

        try {
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(meuEmail, minhaSenha);
                }
            };
            sessao = Session.getInstance(props, auth);
        } catch (Exception ex) {
            System.out.println("Erro" + ex.getMessage());
        }

        try {
            Message message = new MimeMessage(sessao);
            //Quem envia
            message.setFrom(new InternetAddress(meuEmail));

            //Quem recebe
            for (Usuario emails : UsuarioEmail) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(emails.getEmail()));
                message.setSubject("NNH - Desconto disponível!");//Assunto
                message.setText("Olá, estamos com uma oferta de " + desconto + "% de desconto nas diárias!");

                //Método para enviar a mensagem criada
                Transport.send(message);
            }
            System.out.println("Enviada");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void EmailCheckin(String email, int numeroQuarto) {
        Properties props = new Properties();
        //Conexão com servidor Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String meuEmail = "hotelnnh@gmail.com";
        String minhaSenha = "nnh2020!";

        try {
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(meuEmail, minhaSenha);
                }
            };
            sessao = Session.getInstance(props, auth);
        } catch (Exception ex) {
            System.out.println("Erro" + ex.getMessage());
        }

        try {
            Message message = new MimeMessage(sessao);
            //Quem envia
            message.setFrom(new InternetAddress(meuEmail));

            //Quem recebe
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("NNH - Checkin realizado!");//Assunto
            message.setText("Olá, o checkin do quarto número "+numeroQuarto+" foi realizado com sucesso!");

            //Método para enviar a mensagem criada
            Transport.send(message);

            System.out.println("Enviada");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void EmailCheckout(String email, int numeroQuarto) {
        Properties props = new Properties();
        //Conexão com servidor Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String meuEmail = "hotelnnh@gmail.com";
        String minhaSenha = "nnh2020!";

        try {
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(meuEmail, minhaSenha);
                }
            };
            sessao = Session.getInstance(props, auth);
        } catch (Exception ex) {
            System.out.println("Erro" + ex.getMessage());
        }

        try {
            Message message = new MimeMessage(sessao);
            //Quem envia
            message.setFrom(new InternetAddress(meuEmail));

            //Quem recebe
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("NNH - Checkout realizado!");//Assunto
            message.setText("Olá, o checkout do quarto número "+numeroQuarto+" foi realizado com sucesso!");

            //Método para enviar a mensagem criada
            Transport.send(message);

            System.out.println("Enviada");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void EmailCheckoutAntecipado(String email, int numeroQuarto) {
        Properties props = new Properties();
        //Conexão com servidor Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String meuEmail = "hotelnnh@gmail.com";
        String minhaSenha = "nnh2020!";

        try {
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(meuEmail, minhaSenha);
                }
            };
            sessao = Session.getInstance(props, auth);
        } catch (Exception ex) {
            System.out.println("Erro" + ex.getMessage());
        }

        try {
            Message message = new MimeMessage(sessao);
            //Quem envia
            message.setFrom(new InternetAddress(meuEmail));

            //Quem recebe
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("NNH - Checkout antecipado realizado!");//Assunto
            message.setText("Olá, o checkout antecipado do quarto número "+numeroQuarto+" foi realizado com sucesso!");

            //Método para enviar a mensagem criada
            Transport.send(message);

            System.out.println("Enviada");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
