package ch.x42.sling.filehc;

import java.io.File;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.hc.api.HealthCheck;
import org.apache.sling.hc.api.Result;
import org.apache.sling.hc.util.FormattingResultLog;

@Component(immediate=true)
@Service
@Properties({
    @Property(name=HealthCheck.NAME, value="File Detector Health Check"),
    @Property(name=HealthCheck.TAGS, value={ "demo" }),
    @Property(name=HealthCheck.MBEAN_NAME, value="USBFile")
})
public class FileDetectorHealthCheck implements HealthCheck {

    public static final String FILE_PATH = "/Volumes/BERTRAND32G/health-checks-are-great.txt";
    
    public Result execute() {
        final FormattingResultLog log = new FormattingResultLog();
        
        final File f = new File(FILE_PATH);
        log.debug("Using {} as the reference file", f.getAbsolutePath());
        if(f.canRead()) {
            log.info("{} is readable, all good!", f.getAbsolutePath());
        } else {
            log.warn("Ouch - someone removed the USB key? {} not found", f.getAbsolutePath());
            
        }
        
        return new Result(log);
    }
}