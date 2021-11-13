package main;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.util.*;

public class main {

    public static void main(String[] args) {

        String html = "<p>@S1</p>\n" +
                "<h1>제목을 입력하여 주시기 바랍니다.</h1>\n" +
                "<p>부제를 입력하여 주시기 바랍니다.<br>\n" +
                "@S2</p>\n" +
                "<h1>제목을 입력하여 주시기 바랍니다.</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "@S3<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>왼쪽 그리드 제목</h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>오른쪽 그리드 제목</h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "@S4<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>왼쪽 그리드 제목</h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>오른쪽 그리드 제목</h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "@S5<br>\n" +
                "![image](url을 여기에 붙혀넣어주세요)</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h1>제목을 입력하여 주시기 바랍니다.</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "@S6<br>\n" +
                "![image](url을 여기에 붙혀넣어주세요)</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h1>제목을 입력하여 주시기 바랍니다.</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "</ul>";

        String html2 = "<p>@S1</p>\n" +
                "<h1>제목을 입력하여 주시기 바랍니다.</h1>\n" +
                "<p>부제를 입력하여 주시기 바랍니다.</p>\n" +
                "<div><script>alert('h1');</script></div>\n" +
                "<p>@S2</p>\n" +
                "<h1>제목을 입력하여 주시기 바랍니다.</h1>\n" +
                "<div><script>alert('h1');</script></div>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<p>@S3<br>\n" +
                "%</p>\n" +
                "<h2>왼쪽 그리드 제목</h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<script>alert('h1');</script><br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>오른쪽 그리드 제목</h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<p>@S4<br>\n" +
                "%</p>\n" +
                "<h2>왼쪽 그리드 제목</h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>오른쪽 그리드 제목 <script>alert('h1');</script></h2>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<p>@S5<br>\n" +
                "![image](url을 여기에 붙혀넣어주세요)</p>\n" +
                "<h1>제목을 입력하여 주시기 바랍니다.</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "</ul>";

        String html3 = "<p>@S7<br>\n" +
                "!map(https://www.youtube.com/watch?v=7ly6SxwZNTY)<br>\n" +
                "%</p>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>asdf</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf<br>\n" +
                "@S8<br>\n" +
                "!youtube(https://www.youtube.com/watch?v=7ly6SxwZNTY)<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf</p>\n" +
                "</li>\n" +
                "</ul>";

        HtmlParser parser = new HtmlParser();

        List<Content> parsedHtml = parser.getParsedHtml(html3);

        System.out.println(" 파싱된 컨텐츠의 갯수 : " + parsedHtml.size());

        for (Content content : parsedHtml) {
            System.out.println("===  검사 시작  ===");

            System.out.println(" 현재 객체의 코드 :  " + content.getCode() );

            System.out.println(" 현재 객체의 HTML : " + content.getContentTexts());

            System.out.println("===  검사  끝  ===");
        }

    }

}







