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

        String html4 = "<p>@S7<br>\n" +
                "!map(https://maps.google.com)<br>\n" +
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
                "!youtube(https://www.youtube.com)<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>asdf<br>\n" +
                "@S9<br>\n" +
                "<img src=\"https://picsum.photos/536/354\" alt=\"image\" /></p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>표제를 입력해주세요</h2>\n" +
                "<p>내용을 입력해주세요</p>";

        String html5 = "<p>@S7<br>\n" +
                "!map(https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d25706.919801958655!2d127.36067803955085!3d36.35193750000002!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35654be0a6fa05d9%3A0xb0fb5aa41310d05b!2z6rCk65-s66as7JWE67Cx7ZmU7KCQIO2DgOyehOyblOuTnOygkA!5e0!3m2!1sko!2skr!4v1637077295434!5m2!1sko!2skr)<br>\n" +
                "%</p>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "@S8<br>\n" +
                "!youtube(https://www.youtube.com/embed/wgbr7exUnzE)<br>\n" +
                "%</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.<br>\n" +
                "@S9<br>\n" +
                "![image](https://picsum.photos/536/354)</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h2>표제를 입력하여 주시기 바랍니다.</h2>\n" +
                "<p>내용을 입력하여 주시기 바랍니다.</p>";

        HtmlParser parser = new HtmlParser();

        List<Content> parsedHtml = parser.getParsedHtml(html5);

        System.out.println(" 파싱된 컨텐츠의 갯수 : " + parsedHtml.size());

        for (Content content : parsedHtml) {
            System.out.println("===  검사 시작  ===");

            System.out.println(" 현재 객체의 코드 :  " + content.getCode() );

            System.out.println(" 현재 객체의 HTML : " + content.getContentTexts());

            System.out.println("===  검사  끝  ===");
        }

        ArrayList<String> urls = new ArrayList<>();

        urls.add("https://www.youtube.com/watch?v=outThisId");
        urls.add("http://www.youtube.com/watch?v=outThisId");
        urls.add("www.youtube.com/watch?v=outThisId");
        urls.add("youtube.com/watch?v=outThisId");
        urls.add("https://youtu.be/outThisId");
        urls.add("http://youtu.be/outThisId");
        urls.add("https://youtube.com/embed/outThisId");
        urls.add("youtu.be/outThisId");

        for (String url : urls) {

            String s = convertEmbedYoutubeUrl(url);

            System.out.println("유튜브 링크 변환 확인 : " + s);

        }



    }
    /*
    유튜브 임베드 링크 치환 로직
     */
    public static String convertEmbedYoutubeUrl(String url) {

        if ( url.startsWith("https://youtube.com/embed/") || url.startsWith("http://youtube.com/embed/") ) {
            return url;
        } else if ( url.contains("watch?v=") ) {

            String[] splitUrl = url.split("watch\\?v=", 2);

            String videoId = splitUrl[1];

            return "https://youtube.com/embed/" + videoId;

        } else if ( url.startsWith("https://youtu.be/") || url.startsWith("http://youtu.be/") || url.startsWith("youtu.be/") ) {

            String[] splitUrl = url.split("be/", 2);

            String videoId = splitUrl[1];

            return "https://youtube.com/embed/" + videoId;

        }

        return "";
    }



}







