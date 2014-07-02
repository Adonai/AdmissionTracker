package com.adonai.admissiontracker;

import com.adonai.admissiontracker.entities.Favorite;
import com.adonai.admissiontracker.entities.Statistics;

import org.jsoup.nodes.Document;

import java.util.Date;

/**
 * @author Adonai
 */
public interface DataRetriever {

    public static class StudentInfo {
        public Statistics stats;
        public Date admissionDate;
    }

    StudentInfo retrieveStatistics(Favorite fav, Document page) throws Exception;

    BaseFragment getFragment();

}
