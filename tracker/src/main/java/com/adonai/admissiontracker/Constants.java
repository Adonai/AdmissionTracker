package com.adonai.admissiontracker;

import java.text.SimpleDateFormat;

/**
 * Created by adonai on 29.06.14.
 */
public class Constants {
    public final static  String FAVORITE_EXTRA = "offered.favorite";

    public final static  SimpleDateFormat VIEW_FORMAT = new SimpleDateFormat("dd.MM.yyyy\nHH:mm:ss");
    public final static  SimpleDateFormat DDMMHHmm = new SimpleDateFormat("dd.MM HH:mm");

    // Opcodes
    public final static int GET_URL = 0;
    public final static int RELOAD_PAGE = 1;
    public final static int UPDATE_FAVS = 2;
    public final static int NETWORK_ERROR = -1;

    // URLs
    public enum University {
        NONE,
        SPBU("https://cabinet.spbu.ru/Lists/1k_FirstWave/", new SimpleDateFormat("dd.MM.yyyy")),
        SPB_GMU("http://194.226.204.24/abit/statsAll.htm", null),
        ITMO("http://abit.ifmo.ru/ru/stat/172/stat_172.htm", null);

        private String url;
        private SimpleDateFormat timeFormat;

        University(String url, SimpleDateFormat timeFormat) {
            this.url = url;
            this.timeFormat = timeFormat;
        }

        University() {
        }

        public String getUrl() {
            return url;
        }

        public SimpleDateFormat getTimeFormat() {
            return timeFormat;
        }
    }
}
