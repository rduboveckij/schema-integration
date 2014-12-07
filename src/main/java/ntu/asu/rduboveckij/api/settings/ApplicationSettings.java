package ntu.asu.rduboveckij.api.settings;

import java.nio.file.Path;

/**
 * @author andrus.god
 * @since 27.11.2014.
 */
public interface ApplicationSettings {
    boolean isWithReport();

    Path getReportSaveFolder();
}