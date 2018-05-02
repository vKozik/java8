package com.grow.java8.streams;

import com.grow.java8.streams.data.Employee;
import com.grow.java8.streams.enums.Gender;
import com.grow.java8.streams.init.DataInitializer;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamsMain {
    private static Logger logger = LoggerFactory.getLogger(StreamsMain.class);
    public static void main(String[] args) {
        if (args.length == 0){
            logger.error("Error, report code not specified");
            return;
        }
        EmployeeReporter reports = new EmployeeReporter(DataInitializer.init(50));

        switch (args[0]) {
            case "All":
                prinCollection(reports.getAll());
                break;
            case "AllDepartments":
                prinCollection(reports.getAllDepartments());
                break;
            case "AllMen":
                prinCollection(reports.getOneGender(Gender.MALE));
                break;
            case "AllWomen":
                prinCollection(reports.getOneGender(Gender.FEMALE));
                break;
            case "AllChildrenYounger16":
                prinCollection(reports.getAllChildrenYounger16());
                break;
            case "TopSalay5":
                prinCollection(reports.getTopSalay(5));
                break;
            case "Statistics":
                DoubleSummaryStatistics statistics = reports.getStats();
                logger.info("Average salary: " + statistics.getAverage());
                logger.info("Max salary: " + statistics.getMax());
                logger.info("Min salary: " + statistics.getMin());
                break;
            case "WithChildre":
                Map<Boolean, List<Employee>> haveChildren = reports.haveChildren();
                logger.info("have children");
                prinCollection(haveChildren.get(Boolean.TRUE));
                logger.info("Don't have children");
                prinCollection(haveChildren.get(Boolean.FALSE));
                break;
            case "EmployeeByDepartment":
                reports.printEmployeeByDepartment();
                break;
            default:
                logger.error("Report not found");
        }
    }

    private static void prinCollection(Collection<?> collection){
        collection
                .stream()
                .map(String::valueOf)
                .forEach(logger::info);
    }
}
