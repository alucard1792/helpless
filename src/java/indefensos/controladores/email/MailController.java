package indefensos.controladores.email;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Orlando.R.R
 */
@Named(value = "controller")
@SessionScoped
public class MailController implements Serializable {

    
    public MailController() {
    }
    
    private String fromMail;
    private String username;
    private String password;
    private String toMail;
    private String subject;
    private String message;

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String EmailEnviar() {
        try {
            MailSender mailSender=new MailSender();
            mailSender.sendMail("correofixedup@gmail.com", "correofixedup@gmail.com", "fixedup2018", toMail, subject, message);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public void enviarEmailCliente(String toMail, String subject, String message) {
        try {
            MailSender mailSender=new MailSender();
            mailSender.enviarEmailCliente(toMail, subject, message);
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

}
