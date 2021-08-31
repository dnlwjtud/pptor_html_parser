package main;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {


        String html1 = "<p>__S2</p>\r\n"
                + "<h1>테스트</h1>\r\n"
                + "<h2>테스트</h2>\r\n"
                + "<ul>\r\n"
                + "<li>\r\n"
                + "<p>테스트</p>\r\n"
                + "</li>\r\n"
                + "<li></li>\r\n"
                + "</ul>\r\n"
                + "<p>__!<br>\r\n"
                + "__S3</p>\r\n"
                + "<h1>테스트</h1>\r\n"
                + "<h2>테스트</h2>\r\n"
                + "<ul>\r\n"
                + "<li>\r\n"
                + "<p>테스트</p>\r\n"
                + "</li>\r\n"
                + "<li></li>\r\n"
                + "</ul>\r\n"
                + "<p>__!<br>\r\n"
                + "__S5</p>\r\n"
                + "<h1>테스트</h1>\r\n"
                + "<h2>테스트</h2>\r\n"
                + "<ul>\r\n"
                + "<li>\r\n"
                + "<p>테스트</p>\r\n"
                + "</li>\r\n"
                + "<li></li>\r\n"
                + "</ul>\r\n"
                + "<p>__!<br>\r\n"
                + "__S1</p>\r\n"
                + "<h1>테스트</h1>\r\n"
                + "<h2>테스트</h2>\r\n"
                + "<ul>\r\n"
                + "<li>\r\n"
                + "<p>테스트</p>\r\n"
                + "</li>\r\n"
                + "<li></li>\r\n"
                + "</ul>\r\n"
                + "<p>__!<br>\r\n"
                + "__S1</p>\r\n"
                + "<h1>테스트</h1>\r\n"
                + "<h2>테스트</h2>\r\n"
                + "<ul>\r\n"
                + "<li>\r\n"
                + "<p>테스트</p>\r\n"
                + "</li>\r\n"
                + "<li></li>\r\n"
                + "</ul>\r\n"
                + "<p>__!</p>";


        HtmlParser parser = new HtmlParser();

        System.out.println(parser.getParsedHtml(html1));



    }

    public static void htmlParsing(String html) {

        List<String> parsedHTML = new ArrayList<>();

        for ( String arg : html.trim().split("\r\n") ) {
            parsedHTML.add(arg);
        }

        if ( parsedHTML.get(0).trim().contains("S") ) {
            //System.out.println("코드를 찾았습니다.");
            //System.out.println(parsedHTML.get(0).trim());
            String result = extractCodeInHTML(parsedHTML.get(0).trim());

        } else {
            System.out.println("코드를 찾지 못하였습니다.");
        }

        parsedHTML.set(0, "<section>" );

        //System.out.println(parsedHTML.get(parsedHTML.size()-1));

        if ( parsedHTML.get(parsedHTML.size()-1).startsWith("__") ) {
            String parser = parsedHTML.get(parsedHTML.size()-2).replace("<br>", "</p>");
            parsedHTML.set(parsedHTML.size()-2,parser);
            parsedHTML.set(parsedHTML.size()-1, "</section>");
        } else if ( parsedHTML.get(parsedHTML.size()-1).startsWith("<p>") ) {
            parsedHTML.set(parsedHTML.size()-1, "</section>");
        }


        for ( String argS : parsedHTML ) {
            System.out.println(argS);
        }

    }

    public static String extractCodeInHTML(String html) {

        String[] textBits = html.split("<p>");

        String[] textBitBits = textBits[1].split("</p>");

        return textBitBits[0].split("__")[1];

    }

}
