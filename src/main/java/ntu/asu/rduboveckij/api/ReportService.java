package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.api.similarity.SimilarityService;
import ntu.asu.rduboveckij.model.Report;
import ntu.asu.rduboveckij.model.internal.Result;

import java.io.IOException;
import java.util.Set;

/**
 * @author andrus.god
 * @since 26.11.2014.
 */
public interface ReportService {
    public void save(Report report);
}