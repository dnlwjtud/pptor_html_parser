package main;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        String html1 = "<p>@S3</p>\n" +
                "<h1>제목</h1>\n" +
                "<p>부제<br>\n" +
                "% 첫번째 div</p>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>두번째 내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>세번째 내용<br>\n" +
                "% 두번째 div</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>두번째 내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>세번째 내용<br>\n" +
                "@S3</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h1>제목</h1>\n" +
                "<p>부제<br>\n" +
                "% 첫번째 div</p>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>두번째 내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>세번째 내용<br>\n" +
                "% 두번째 div</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>두번째 내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>세번째 내용</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<p>@S3</p>\n" +
                "<h1>제목</h1>\n" +
                "<p>부제<br>\n" +
                "%<br>\n" +
                "첫번째 div</p>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>두번째 내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>세번째 내용<br>\n" +
                "%<br>\n" +
                "두번째 div<br>\n" +
                "내용<br>\n" +
                "두번째 내용<br>\n" +
                "세번째 내용</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<p>@S3</p>\n" +
                "<h1>제목</h1>\n" +
                "<p>부제<br>\n" +
                "%<br>\n" +
                "첫번째 div</p>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>두번째 내용</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>세번째 내용<br>\n" +
                "%<br>\n" +
                "두번째 div<br>\n" +
                "내용<br>\n" +
                "두번째 내용<br>\n" +
                "세번째 내용</p>\n" +
                "</li>\n" +
                "</ul>";

        String html2 = "<p>@S1</p>\n" +
                "<h1>표제</h1>\n" +
                "<p>부제<br>\n" +
                "@S2</p>\n" +
                "<h1>표제</h1>\n" +
                "<p>많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용많은내용<br>\n" +
                "@S3</p>\n" +
                "<h1>표제</h1>\n" +
                "<p>%</p>\n" +
                "<h1>왼쪽제목</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용1</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용2</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용3</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<h1>오른쪽내용</h1>\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>내용1</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용2</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>내용3<br>\n" +
                "@S5<br>\n" +
                "이미지 태그만 먹힙니다<br>\n" +
                "<img><br>\n" +
                "@S6<br>\n" +
                "이미지 태그만 먹힙니다<br>\n" +
                "<img></p>\n" +
                "</li>\n" +
                "</ul>";

        HtmlParser parser = new HtmlParser();

        List<Content> parsedHtml = parser.getParsedHtml(html2);

        for ( Content content : parsedHtml) {

            System.out.println(" 객체 시작 ");

            System.out.println(" 객체 코드 : " + content.getCode());

            System.out.println( " 객체 컨텐츠 : ");
            for ( String arg : content.getContentTexts() ) {
                System.out.println(arg);
            }

            System.out.println(" 객체 끝 ");
        }


    }

}
