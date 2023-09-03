package io.vamani.application.scheduler;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.EmployeeInformationUpdate;
import io.vamani.application.repository.EmployeeInformationUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
public class EmployeeInformationUpdateSchedular {

    @Autowired
    private Environment env;

    @Autowired
    private EmployeeInformationUpdateRepository employeeInformationUpdateRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    @Scheduled(cron = "0 30 17 * * ?")
    public Boolean pushEmployeeInformation() throws SQLException, MalformedURLException, IOException, NoSuchAlgorithmException, KeyManagementException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        List<EmployeeInformationUpdate> employeeInformationUpdates = employeeInformationUpdateRepository.findAllUnProcess();
        if (employeeInformationUpdates != null && employeeInformationUpdates.size() > 0) {
            SingleConnectionDataSource dataSource = null;
            SingleConnectionDataSource dataSourceBata = null;
            SingleConnectionDataSource dataSourceB4 = null;
            SingleConnectionDataSource dataSourceC3 = null;
            try {
                // 169 & 137
                dataSource = new SingleConnectionDataSource();
                dataSource.setDriverClassName(env.getProperty("partner.datasource.driver-class-name"));
                dataSource.setUrl("jdbc:sqlserver://172.16.111.26:1433;database=pulse");
                dataSource.setUsername("mmcadmin");
                dataSource.setPassword("mmc@123");
                dataSource.setAutoCommit(true);

                // Bata
                dataSourceBata = new SingleConnectionDataSource();
                dataSourceBata.setDriverClassName(env.getProperty("partner.datasource.driver-class-name"));
                dataSourceBata.setUrl("jdbc:sqlserver://VG01IT000405\\SQLEXPRESS:1433;database=payroll_01_19");
                dataSourceBata.setUsername("sa");
                dataSourceBata.setPassword("sa");
                dataSourceBata.setAutoCommit(true);

                // B4
                dataSourceB4 = new SingleConnectionDataSource();
                dataSourceB4.setDriverClassName(env.getProperty("partner.datasource.driver-class-name"));
                dataSourceB4.setUrl("jdbc:sqlserver://STORE\\B4MMC:1433;database=payroll_01_19");
                dataSourceB4.setUsername("sa");
                dataSourceB4.setPassword("Vamani@SA");
                dataSourceB4.setAutoCommit(true);

                // C3
                dataSourceC3 = new SingleConnectionDataSource();
                dataSourceC3.setDriverClassName(env.getProperty("partner.datasource.driver-class-name"));
                dataSourceC3.setUrl("jdbc:sqlserver://HRP\\MMC:1433;database=payroll_01_19");
                dataSourceC3.setUsername("sa");
                dataSourceC3.setPassword("Vamani@SA");
                dataSourceC3.setAutoCommit(true);

                for (EmployeeInformationUpdate employeeInformationUpdate : employeeInformationUpdates) {
                    if (employeeInformationUpdate.getImagePath() != null && employeeInformationUpdate.getImagePath().length() > 0) {
                        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
                        URL url = new URL(employeeInformationUpdate.getOldImagePath());
                        URLConnection urlConnection = url.openConnection();
                        urlConnection.setRequestProperty("User-Agent", USER_AGENT);
                        try (BufferedInputStream in = new BufferedInputStream(urlConnection.getURL().openStream());
                             FileOutputStream fileOutputStream = new FileOutputStream(UPLOADED_FOLDER + "personal/old/" + employeeInformationUpdate.getImagePath())) {
                            byte dataBuffer[] = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                                fileOutputStream.write(dataBuffer, 0, bytesRead);
                            }
                            employeeInformationUpdate.setOldImagePath(employeeInformationUpdate.getImagePath());

                            Path source = Paths.get(UPLOADED_FOLDER + "personal/" + employeeInformationUpdate.getImagePath());
                            String ext = employeeInformationUpdate.getImagePath().substring(employeeInformationUpdate.getImagePath().lastIndexOf("."), employeeInformationUpdate.getImagePath().length());
                            String newFile = employeeInformationUpdate.getUserCode().getLogin() + ext;
                            Path newDir = Paths.get(UPLOADED_FOLDER + "personal/deploy/");
                            Files.copy(source, newDir.resolve(newFile));
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    if (employeeInformationUpdate.getUserCode() != null && employeeInformationUpdate.getUserCode().getLogin() != null) {
                        String empCode = employeeInformationUpdate.getUserCode().getLogin();
                        if (empCode != null && (empCode.startsWith("101") || empCode.startsWith("190") || empCode.startsWith("191"))) {
                            try (Connection conn = dataSource.getConnection()) {
                                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [payroll_01_19].[dbo].[employee] set add1=? and phone=? where card_no=?");
                                preparedStatement.setString(1, employeeInformationUpdate.getCorrespondenceAddress());
                                preparedStatement.setString(2, employeeInformationUpdate.getMobileNumber());
                                preparedStatement.setString(3, empCode);
                                int result = preparedStatement.executeUpdate();
                                if (result > 0) {
                                    employeeInformationUpdate.setProcessFlag("Y");
                                    employeeInformationUpdate.setProcessDate(Instant.now());
                                    employeeInformationUpdateRepository.save(employeeInformationUpdate);
                                }
                            }
                        } else if (empCode != null && empCode.startsWith("102")) {
                            try (Connection conn = dataSource.getConnection()) {
                                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [payroll_02_19].[dbo].[employee] set add1=?, phone=? where card_no=?");
                                preparedStatement.setString(1, employeeInformationUpdate.getCorrespondenceAddress());
                                preparedStatement.setString(2, employeeInformationUpdate.getMobileNumber());
                                preparedStatement.setString(3, empCode);
                                int result = preparedStatement.executeUpdate();
                                if (result > 0) {
                                    employeeInformationUpdate.setProcessFlag("Y");
                                    employeeInformationUpdate.setProcessDate(Instant.now());
                                    employeeInformationUpdateRepository.save(employeeInformationUpdate);
                                }
                            }
                        } else if (empCode != null && empCode.startsWith("103")) {
                            try (Connection conn = dataSourceBata.getConnection()) {
                                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [payroll_01_19].[dbo].[employee] set add1=?, phone=? where card_no=?");
                                preparedStatement.setString(1, employeeInformationUpdate.getCorrespondenceAddress());
                                preparedStatement.setString(2, employeeInformationUpdate.getMobileNumber());
                                preparedStatement.setString(3, empCode);
                                int result = preparedStatement.executeUpdate();
                                if (result > 0) {
                                    employeeInformationUpdate.setProcessFlag("Y");
                                    employeeInformationUpdate.setProcessDate(Instant.now());
                                    employeeInformationUpdateRepository.save(employeeInformationUpdate);
                                }
                            }
                        } else if (empCode != null && empCode.startsWith("104")) {
                            try (Connection conn = dataSourceC3.getConnection()) {
                                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [payroll_01_19].[dbo].[employee] set add1=?, phone=? where card_no=?");
                                preparedStatement.setString(1, employeeInformationUpdate.getCorrespondenceAddress());
                                preparedStatement.setString(2, employeeInformationUpdate.getMobileNumber());
                                preparedStatement.setString(3, empCode);
                                int result = preparedStatement.executeUpdate();
                                if (result > 0) {
                                    employeeInformationUpdate.setProcessFlag("Y");
                                    employeeInformationUpdate.setProcessDate(Instant.now());
                                    employeeInformationUpdateRepository.save(employeeInformationUpdate);
                                }
                            }
                        } else if (empCode != null && empCode.startsWith("105")) {
                            try (Connection conn = dataSourceB4.getConnection()) {
                                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [payroll_01_19].[dbo].[employee] set add1=?, phone=? where card_no=?");
                                preparedStatement.setString(1, employeeInformationUpdate.getCorrespondenceAddress());
                                preparedStatement.setString(2, employeeInformationUpdate.getMobileNumber());
                                preparedStatement.setString(3, empCode);
                                int result = preparedStatement.executeUpdate();
                                if (result > 0) {
                                    employeeInformationUpdate.setProcessFlag("Y");
                                    employeeInformationUpdate.setProcessDate(Instant.now());
                                    employeeInformationUpdateRepository.save(employeeInformationUpdate);
                                }
                            }
                        }
                    }
                }
            } catch(Exception e) {
                System.out.println("EmployeeInformationUpdateSchedular" + e.getMessage());
            } finally {
                if (dataSource != null) {
                    try {
                        dataSource.destroy();
                    } catch (Exception e) {
                    }
                }
                if (dataSourceBata != null) {
                    try {
                        dataSourceBata.destroy();
                    } catch (Exception e) {
                    }
                }
                if (dataSourceB4 != null) {
                    try {
                        dataSourceB4.destroy();
                    } catch (Exception e) {
                    }
                }
                if (dataSourceC3 != null) {
                    try {
                        dataSourceC3.destroy();
                    } catch (Exception e) {
                    }
                }
            }
        }
        return true;
    }
}
