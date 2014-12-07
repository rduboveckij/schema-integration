package ntu.asu.rduboveckij.service.settings;

import ntu.asu.rduboveckij.api.settings.ApplicationSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author andrus.god
 * @since 27.11.2014.
 */
@Component
public class ApplicationSettingsImpl implements ApplicationSettings {
    private volatile boolean withReport;
    private volatile Path reportSaveFolder;

    @Override
    public boolean isWithReport() {
        return withReport;
    }

    @Value("#{environment['application.withReport']}")
    public void setWithReport(boolean withReport) {
        this.withReport = withReport;
    }

    @Override
    public Path getReportSaveFolder() {
        return reportSaveFolder;
    }

    @Value("#{environment['application.reportSaveFolder']}")
    public void setReportSaveFolder(String reportSaveFolder) throws IOException {
        this.reportSaveFolder = Paths.get(reportSaveFolder);
        File file = this.reportSaveFolder.toFile();
        if (!file.exists() && !file.mkdir())
            throw new IOException("Folder is not created in path = " + file.getAbsolutePath());
    }
}