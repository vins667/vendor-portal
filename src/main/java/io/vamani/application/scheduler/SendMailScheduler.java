package io.vamani.application.scheduler;

import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableScheduling
public class SendMailScheduler {
    @Autowired
    private MailService mailService;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

     /*@Scheduled(cron = "0 55 17 * * ?")*/
    public void sendMails() {
        String subject = "IT Security --Information and Awareness";
        String body = "<p><span style=\"color: #ff0000;\">--------------------------------------IT Security --Information and Awareness------------------</span></p>\n" +
            "<p>Requested&nbsp;all system users to change their passwords&nbsp;to access&nbsp;System/Software/Application.</p>\n" +
            "<div>\n" +
            "<p>The Passwords should have at least 8 characters, include uppercase and lowercase letters, numbers, and symbols.</p>\n" +
            "</div>\n" +
            "<div>\n" +
            "<p>** Do not share your password with anyone.</p>\n" +
            "</div>\n" +
            "<div>\n" +
            "<p>** If you have any questions or concerns related to system, please contact : <a href=\"mailto:it.helpdesk@vamanioverseas.com\" target=\"_blank\" rel=\"noopener noreferrer\" data-auth=\"NotApplicable\">it.helpdesk@vamanioverseas.com</a>&nbsp;or local IT Executive.</p>\n" +
            "</div>\n" +
            "<div>\n" +
            "<p>Thanks &amp; Regards</p>\n" +
            "<p>IT Security Team</p>\n" +
            "</div>";
        List<EmployeeView> employeeViewList = employeeViewRepository.findByFactoryAndEmailNotNull("102");
        int ctr = 0;
        for(EmployeeView employeeView : employeeViewList) {
            if(employeeView.getCardNo() != null && !employeeView.getCardNo().equalsIgnoreCase("102000005") && employeeView.getEmail() != null && employeeView.getEmail().length()>0) {
                try {
                    mailService.sendEmail(employeeView.getEmail(), subject, body, true, true);
                    ++ctr;
                    System.out.println("Success " + ctr + " Email: " + employeeView.getEmail());
                } catch (Exception e) {
                    ++ctr;
                    System.out.println("Error " + ctr + " Email: " + employeeView.getEmail());
                }
            }
        }
        // mailService.sendEmail("anilkalra@vamanioverseas.com", subject, body, true, true);
    }
}
