import io.vamani.application.domain.VcutMainEntryAllow;
import io.vamani.application.domain.VcutMainEntryMaster;
import io.vamani.application.domain.VcutStylePlanUpload;
import io.vamani.application.repository.VcutMainEntryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class TestEntry {
    @Autowired
    private static VcutMainEntryMasterRepository vcutMainEntryMasterRepository;
    public static void main(String[] args) throws ParseException {

        BigDecimal orgSecStockBg = new BigDecimal("10.2312");
        orgSecStockBg = orgSecStockBg.setScale(3, RoundingMode.UP);
        System.out.println(orgSecStockBg.doubleValue());
        System.out.println(new java.sql.Time(Date.from(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toInstant()).getTime()));
        /*VcutMainEntryMaster entryMaster = new VcutMainEntryMaster();
        entryMaster.setVcutStylePlanUpload(new VcutStylePlanUpload(31L));
        entryMaster.setEntryBy("102000046");
        entryMaster.setEntryTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("05/11/2019 09:01:10").toInstant());
        entryMaster.entryType("FTT");
        vcutMainEntryMasterRepository.save(entryMaster);*/
    }
}
