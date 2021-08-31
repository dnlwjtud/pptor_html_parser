package main;

import java.util.ArrayList;
import java.util.List;

public class HtmlParser {

    // 추출한 코드를 저장할 필드
    private List<String> extractedCodes;
    // 상태코드를 저장할 필드
    private int status;

    // 결과물 저장할 필드
    private List<Content> result;

    /*
    생성자
     */
    public HtmlParser() {

        // 상태코드 0으로 초기화
        this.status = 0;
        // 리턴해줄 ArrayList를 생성
        this.result = new ArrayList<>();

        // 코드를 한거번에 저장할 ArrayList 생성
        this.extractedCodes = new ArrayList<>();

    }

    public List<Content> getParsedHtml(String html) {

        List<String> htmlLines = splitHTML(html);

        readHtmlLine(htmlLines);

        return this.result;
    }

    /*
     * HTML 문자열을 한줄씩 스플릿
     */
    private List<String> splitHTML(String html) {

        // 스플릿 한 HTML을 저장할 List 생성
        List<String> htmlLines = new ArrayList<>();

        // HTML 문자열을 줄바꿈 기준으로 스플릿
        for (String htmlLine : html.trim().split("\n")) {
            htmlLines.add(htmlLine);
        }
        
        // 스플릿 한 HTML을 리턴
        return htmlLines;

    }

    /*
     HTML 파싱 메소드
     */
    private void readHtmlLine(List<String> htmlLines) {

        System.out.println("=== 파싱 메소드 정상 구동 ====");

        while (this.status < 1) {

            // 코드와 슬라이드 내부에 들어갈 html 요소를 보관할 인스턴스 Content 생성 
            Content content = new Content();
            // Content를 보관할 List를 생성
            List<String> contentTexts = new ArrayList<>();

            for (String line : htmlLines) {

                System.out.println("현재 읽고 있는 라인 : " + line);

                // 시작코드 추출
                if (line.trim().contains("@S") || line.trim().contains("@s")) {

                    if ( extractCode(line).startsWith("S") ) {
                        // 인스턴스에 코드 세팅
                        this.extractedCodes.add(extractCode(line));
                        String lineCode = extractCode(line);
                        System.out.println("코드를 성공적으로 추출하였습니다 : " + lineCode);

                        // 닫는태그 추가
                        String close = "</section>";
                        contentTexts.add(close);
                        System.out.println("닫는태그를 추가하였습니다." + close);

                        // html 에 섹션에 코드 클래스 추가하여 배열에 추가
                        line = "<section " + "id=" + '"' + lineCode + '"' + ">";
                        contentTexts.add(line);
                        System.out.println("섹션으로 변환되었습니다 : " + line);
                        continue;

                    }

                }

                System.out.println("추가한 라인 : " +line);
                contentTexts.add(line);

            }

            // section 갈무리
            contentTexts.remove(0);
            contentTexts.add(contentTexts.size(),"</section>");

            int count = 0;
            List<String> args = new ArrayList<>();

            for ( String contentText : contentTexts ) {

                if ( contentText.contains("<section") ) {
                    args.add(contentText);
                    content.setCode(extractedCodes.get(count));
                    count++;
                    continue;
                }

                if (contentText.equals("</section>")) {
                    args.add(contentText);
                    content.setContentTexts(args);
                    result.add(content);

                    content = new Content();
                    args = new ArrayList<>();
                    continue;
                }

                args.add(contentText);

            }

            status = 1;
            System.out.println("정상 파싱 완료!");

            System.out.println("=== 판독기 호출 성공, 로직 끝 ====");

        }

    }

    private String extractCode(String line) {

        if ( !line.startsWith("<p>") ) {
            line = "<p>" + line;
        }

        if ( line.endsWith("<br>") ) {
            String[] lineBits = line.split("<br>");
            line = lineBits[0] + "</p>";
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

                    return codeBits[1];

                } catch (NumberFormatException e ) {
                    return line;
                }

            } else {
                return line;
            }
        } else {
            return line;
        }

    }

}
