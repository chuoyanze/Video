package com.probie.video.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 12:50
 * @description：
 * @modified By：
 * @version: $
 */
public class HtmlParse {

    public static Document getDocument(String url ){

        try {
            Document document = Jsoup.connect(url).get();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
