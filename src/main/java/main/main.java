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

        String html2 = "<p>@S3<br>\n" +
                "% 바로해보는<br>\n" +
                "% 두번째해본</p>\n" +
                "<p>@S4</p>\n" +
                "<h1>S4코드는?</h1>\n" +
                "<p>당연 노프라블럼?<br>\n" +
                "% 테스트1<br>\n" +
                "% 테스트2</p>\n" +
                "<p>@S3</p>\n" +
                "<h1>그럼 이건..?</h1>\n" +
                "<p>붙여쓰면?</p>\n" +
                "<p>%이렇게쓰면?<br>\n" +
                "%그냥잘리나?</p>";


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
