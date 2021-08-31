package main;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        /*
        String test = "<p>@S1<br>";

        if ( test.endsWith("<br>") ) {
            String[] testA = test.split("<br>");

            for ( String testBit : testA ) {
                System.out.println("1 : " + testBit);
            }

            test = testA[0] + "</p>";

        }

        System.out.println(test);

         */


        String html1 = "<p>@S1</p>\n" +
                "<h1>제목</h1>\n" +
                "<p>부제목<br>\n" +
                "@S1</p>\n" +
                "<h1>슬라이드2</h1>\n" +
                "<p>= 그리드 1<br>\n" +
                "내용<br>\n" +
                "내용<br>\n" +
                "내용<br>\n" +
                "= 그리드 2<br>\n" +
                "내용<br>\n" +
                "내용<br>\n" +
                "내용</p>\n" +
                "<p>@S1</p>\n" +
                "<h1>경우의 수2</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>이럴땐 어떻게나올까요?<br>\n" +
                "@S2</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h1>바로이렇게</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>다음에는?</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<p>@S1</p>\n" +
                "<h1>이런경우는?</h1>\n" +
                "<p>@S2</p>\n" +
                "<h1>경우의수 3</h1>\n" +
                "<p>@S1</p>\n" +
                "<h1>이거는</h1>\n" +
                "<p>@S2<br>\n" +
                "어떻게나오지</p>\n" +
                "<p>@S1<br>\n" +
                "@S2<br>\n" +
                "빈 객체 하나와 들어있는 객체하나</p>";


        HtmlParser parser = new HtmlParser();

        System.out.println(parser.getParsedHtml(html1));

    /*
        String test = "<p>@S1</p>";

        String[] testBits = test.split("<p>");

        String[] testBitBits = testBits[1].split("</p>");

        for (String arg : testBitBits ) {
            System.out.println("1 : " + arg);
            System.out.println(testBitBits.length);
        }

    */
        /*
        String test1 = "<p>@s1</p>";
        String test2 = "asdf@S2</p>";
        String test3 = "<p>@sageawegaweg</p>";

        extractCode(test1);
        extractCode(test2);
        extractCode(test3);

         */
    }
/*
    private static void extractCode(String line) {

        if ( !line.startsWith("<p>") ) {
            line = "<p>" + line;
        }

        String[] lineBit = line.split("<p>");

        String[] lineBitBits = lineBit[1].split("</p>");

        if ( lineBitBits.length == 1 ) {
            if ( lineBitBits[0].startsWith("@S") || lineBitBits[0].startsWith("@s") ) {
                String arg = lineBitBits[0].trim().toUpperCase();

                String[] argBits = arg.split("@S");

                try {
                    Integer.parseInt(argBits[1].trim());
                    String[] codeBits = arg.split("@");

                    System.out.println(codeBits[1]);

                } catch (NumberFormatException e ) {
                    System.out.println(line);
                }

            } else {
                System.out.println(line);
            }
        } else {
            System.out.println(line);
        }

    }

*/
}
