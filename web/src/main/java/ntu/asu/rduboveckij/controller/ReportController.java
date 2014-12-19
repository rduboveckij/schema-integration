package ntu.asu.rduboveckij.controller;

import ntu.asu.rduboveckij.api.ReportService;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author andrus.god
 * @since 15.12.2014.
 */
@RestController
public class ReportController {
    @Inject
    private ReportService reportService;
}