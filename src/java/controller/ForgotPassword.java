package controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import model.DAOAccount;

@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgotpw"})
public class ForgotPassword extends HttpServlet {

    private final String fromEmail = "thereroll.001@gmail.com";
    private final String password = "qfxs hyue wsap npou";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        DAOAccount dao = new DAOAccount();
        HttpSession httpSession = request.getSession();

        try {
            if (email == null || email.isEmpty()) {
                request.setAttribute("mess", "Please enter your email address!");
                request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
                return;
            }

            if (!dao.isEmailExist(email)) {
                request.setAttribute("mess", "This email is not registered! Please try another email!");
                request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
                return;
            }

            Random random = new Random();
            int OTPValue = 100000 + random.nextInt(900000); // Ensure a 6-digit OTP

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true"); // Enable debug mode

            Session mailSession = Session.getInstance(props, new jakarta.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress(fromEmail));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                message.setSubject("Password Reset OTP");
                message.setText("Your OTP for password reset is: " + OTPValue);

                System.out.println("Attempting to send email to: " + email);
                Transport.send(message);
                System.out.println("OTP email sent successfully");

                httpSession.setAttribute("otp", OTPValue);
                httpSession.setAttribute("email", email);
                request.setAttribute("mess", "OTP has been sent to your email.");
                request.getRequestDispatcher("OTPConfirm.jsp").forward(request, response);

            } catch (Exception e) {
                System.out.println("Error sending email: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("mess", "Failed to send OTP. Error: " + e.getMessage());
                request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println("General error in ForgotPassword servlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("mess", "An error occurred. Please try again later.");
            request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
