package main;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        this.status = 0;
        this.result = new ArrayList<>();
        this.extractedCodes = new ArrayList<>();

    }

    /*
    결과물 리턴 메소드
     */
    public List<Content> getParsedHtml(String html) {

        List<String> htmlLines = splitHTML(html);

        readHtmlLine(htmlLines);

        //System.out.println("== 컨텐츠 검사 시작 == ");
        checkContent(this.result);
        //System.out.println("== 컨텐츠 검사 끝 == ");

        //System.out.println(" == XSS 검사 시작 == ");
        checkXSS(this.result);
        //System.out.println(" == XSS 검사 시작 == ");


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

        //System.out.println("=== 파싱 메소드 정상 구동 ====");

        while (this.status < 1) {

            // 코드와 슬라이드 내부에 들어갈 html 요소를 보관할 인스턴스 Content 생성 
            Content content = new Content();
            // Content를 보관할 List를 생성
            List<String> contentTexts = new ArrayList<>();

            for (String line : htmlLines) {

                //System.out.println("현재 읽고 있는 라인 : " + line);

                // 시작코드 추출
                if (line.trim().contains("@S") || line.trim().contains("@s")) {

                    if ( extractCode(line).startsWith("S") ) {
                        // 인스턴스에 코드 세팅
                        this.extractedCodes.add(extractCode(line));
                        String lineCode = extractCode(line);
                        //System.out.println("코드를 성공적으로 추출하였습니다 : " + lineCode);

                        // 닫는태그 추가
                        String close = "</section>";
                        contentTexts.add(close);
                        //System.out.println("닫는태그를 추가하였습니다." + close);

                        // html 에 섹션에 코드 클래스 추가하여 배열에 추가
                        line = "<section " + "class=" + '"' + lineCode + '"' + ">";
                        contentTexts.add(line);
                        //System.out.println("섹션으로 변환되었습니다 : " + line);
                        continue;

                    }

                }

                //System.out.println("추가한 라인 : " +line);
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
            //System.out.println("정상 파싱 완료!");

            //System.out.println("=== 판독기 호출 성공, 로직 끝 ====");

        }

    }

    /*
    코드 추출 및 코드 태그 유효성 판별
     */
    private String extractCode(String line) {

        String lineText = removeHTMLTag(line);

        if ( lineText.startsWith("@S") || lineText.startsWith("@s") ) {

            lineText = lineText.toUpperCase();

            String[] codeBits1 = lineText.split("@S");

            try {

                Integer.parseInt(codeBits1[1].trim());
                String[] codeBits2 = lineText.split("@");

                return codeBits2[1];

            } catch (NumberFormatException e ) {
                return line;
            }
        } else {
            return line;
        }

    }

    /*
    태그 벗기기
     */
    public String removeHTMLTag(String line) {
        // 정규표현식을 이용하여 HTML 태그 제거
        return line.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    /*
    컨텐츠 필터링
    */
    public List<Content> checkXSS(List<Content> slides) {

        String originHTML = "";
        String filteredHTML = "";
        List<String> filteredContentTexts = new ArrayList<>();

        // 비어있는 리스트 추가
        List<Content> checkedSlides = new ArrayList<>();

        // 필터 생성
        Safelist safelist = Safelist.relaxed();
        safelist.addTags("section")
                .addTags("div")
                .addTags("iframe")
                .addTags("figure")
                .addTags("figcaption")
                .addTags("svg")
                .addTags("use")
                .addTags("span")
                .addAttributes("span", "style")
                .addAttributes("use","xlink:href")
                .addAttributes("iframe", "src")
                .addAttributes("section", "class");


        // 모든 객체 필터 시작
        for (Content originSlide : slides) {

            // 객체에서 컨텐츠 텍스트 뽑기
            List<String> originSlideContentTexts = originSlide.getContentTexts();

            // 검사전 하나의 스트링으로 병합
            for (String originSlideContentText : originSlideContentTexts) {
                // 원본 html
                originHTML += originSlideContentText;
            }

            // 필터링
            filteredHTML = Jsoup.clean(originHTML,safelist);

            String[] splitFilteredHTML = filteredHTML.split("\n");

            for (String arg : splitFilteredHTML) {
                filteredContentTexts.add(arg);
            }

            originSlide.setContentTexts(filteredContentTexts);

            checkedSlides.add(originSlide);

            originHTML = "";
            filteredHTML = "";
            filteredContentTexts = new ArrayList<>();
            
        }

        return checkedSlides;
    }

    /*
    코드별 컨텐츠 체킹
     */
    public List<Content> checkContent(List<Content> slides) {

        List<Content> checkedSlides = new ArrayList<>();

        for ( Content slide : slides ) {

            if ( !slide.getContentTexts().get(0).startsWith("<section")) {
                return slides;
            }

            switch ( slide.getCode() ) {
                case "S3" :
                case "S4" :
                    slide.setContentTexts(addDiv(slide.getContentTexts()));
                    break;
                case "S7" :
                case "S8" :
                    slide.setContentTexts(addDiv(slide.getContentTexts()));
                    slide.setContentTexts(addEmbedItem(slide.getContentTexts()));
                    break;
                case "S9" :
                    slide.setContentTexts(convertSpan(slide.getContentTexts()));
                    break;
                default:
                    checkedSlides.add(slide);
                    break;
            }

        }

        return checkedSlides;
    }

    // 구분선 //

    /*
    img 태그를 span태그로 변환
     */
    private List<String> convertSpan(List<String> contentTexts) {

        List<String> result = new ArrayList<>();

        String span = "";

        for (String contentText : contentTexts) {

            if ( contentText.startsWith("<img") ) {

                String itemUrl = extractUrl(contentText);
                System.out.println("추출된 이미지 URL : " + itemUrl);

                span = "<span class=\"background anim\" " +
                        "style=\"background-image:url('" +
                        itemUrl +
                        "')\">" +
                        "</span>";

                contentText = "";

                result.add(contentText);
                continue;

            }

            result.add(contentText);

        }

        result.add(1, span);

        return result;
    }

    /*
    임베드 아이템 추가
     */
    private List<String> addEmbedItem(List<String> contentTexts) {

        System.out.println(" ==  임베드 아이템 필터 추가 시작 == ");

        List<String> result = new ArrayList<>();

        String itemUrl = "";

        for (String contentText : contentTexts) {

            System.out.println(" 임베드 아이템 로직 현재 읽고 있는 라인 : " + contentText);

            String pureContentText = removeHTMLTag(contentText);

            // 임시
            if ( !pureContentText.equals("") ) {
                System.out.println(" 임베드 아이템 로직 현재 읽고 있는 라인의 텍스트 : " + pureContentText);    
            }

            if ( isValidEmbedCode(pureContentText) && !extractEmbedCode(pureContentText).equals("")) {

                System.out.println(" 임베드 아이템 코드가 포함된 텍스트 검출 : " + pureContentText);

                switch ( extractEmbedCode(pureContentText) ) {
                    case "map":
                        System.out.println("임베드 아이템 코드 = " + extractEmbedCode(pureContentText));
                        itemUrl = extractUrl(pureContentText);
                        contentText = makeEmbedItem("map", itemUrl);

                        if ( !itemUrl.contains("https://maps.google.com") ) {
                            contentText = "";
                        }

                        System.out.println("result add EMBED map : " + contentText);
                        result.add(contentText);
                        continue;
                    case "youtube":
                        System.out.println("임베드 아이템 코드 = " + extractEmbedCode(pureContentText));
                        itemUrl = extractUrl(pureContentText);
                        contentText = makeEmbedItem("youtube", itemUrl);

                        if ( !checkValidYoutubeUrl(itemUrl) ) {
                            contentText = "";
                        }

                        System.out.println("result add EMBED youtube : " + contentText);
                        result.add(contentText);
                        continue;
                    default:
                        continue;
                }

            }

            System.out.println(" result add : " + contentText);
            result.add(contentText);

        }

        return result;
    }

    /*
    유튜브 링크 검사 로직
     */
    private boolean checkValidYoutubeUrl(String url) {

        return url.startsWith("https://www.youtube.com/watch?v=") ||
                url.startsWith("https://www.youtube.com") ||
                url.startsWith("http://www.youtube.com/watch?v=") ||
                url.startsWith("http://www.youtube.com") ||
                url.startsWith("www.youtube.com/watch?v=") ||
                url.startsWith("youtube.com/watch?v=") ||
                url.startsWith("https://youtu.be/") ||
                url.startsWith("http://youtu.be/") ||
                url.startsWith("youtu.be/");

    }

    /*
    ! 문법 유효성 검사
     */
    private boolean isValidEmbedCode(String text) {

        System.out.println("올바른 임베드 코드인지 검사를 시작합니다.");
        // !를 기준으로 텍스트를 분리
        String[] splitText = text.split("!");

        if ( splitText.length != 2 ) {
            System.out.println("옳지 않은 형식입니다.");
            return false;
        }

        // 지정한 코드일때는 true, 아닐때는 false
        if ( splitText[1].startsWith("map") || splitText[1].startsWith("youtube") ) {
            System.out.println("임베드 코드가 포함되어 있습니다");
            return true;
        } else {
            System.out.println("임베드 코드가 포함되어 있지 않습니다");
            return false;
        }

    }

    /*
    ! 문법 코드 추출
     */
    private String extractEmbedCode(String text) {

        System.out.println("임베드 코드를 추출합니다.");
        String[] splitText = text.split("!");

        if ( splitText.length != 2 ) {
            System.out.println("옳지 않은 형식입니다.");
            return text;
        }

        if ( splitText[1].toLowerCase().startsWith("map") ) {
            System.out.println("추출된 코드 map");
            return "map";
        } else if ( splitText[1].toLowerCase().startsWith("youtube") ) {
            System.out.println( "추출된 코드 youtube");
            return "youtube";
        } else {
            System.out.println("옳지 않은 형식입니다.");
            return text;
        }

    }

    /*
    URL 추출
     */
    private String extractUrl(String text) {

        System.out.println("url 을 추출합니다");

        try {
            String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
            Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(text);
            if (m.find()) {
                System.out.println("추출된 url : " + m.group());
                return m.group();
            }
            System.out.println(" 걸린url 이 없습니다.");
            return "";
        } catch (Exception e) {
            System.out.println("예외로 인하여 추출된 url 이 없습니다.");
            return "";
        }

    }

    /*
    임베드 아이템 코드
     */
    private String makeEmbedItem(String code, String url) {

        String item = "";

        switch ( code ) {
            case "map" :
                item = "<figure>\n" +
                        "<iframe width=\"670\" height=\"500\" id=\"gmap_canvas\" " +
                        "src=\" " + url + "\" " +
                        "frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\"></iframe>\n" +
                        "<figcaption>\n" +
                        "<a href=\"https://maps.google.com\" title=\"Google Maps\">\n" +
                        "<svg class=\"fa-maps\">\n" +
                        "<use xlink:href=\"#fa-maps\"></use>\n" +
                        "</svg>\n" +
                        "Google Maps\n" +
                        "</a>\n" +
                        "</figcaption>\n" +
                        "</figure>";

                System.out.println(" 생성된 아이템 : " + item);
                return item;

            case "youtube" :
                item = "<iframe width=\"670\" height=\"500\" " +
                        "src= \" " + url + "\" " +
                        "title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";

                System.out.println(" 생성된 아이템 : " + item);
                return item;

            default:
                System.out.println("생성된 아이템 : " + item);
                return item;

        }

    }

    // 구분선 //


    /*
    DIV 추가
     */
    public List<String> addDiv(List<String> contentTexts) {

        System.out.println(" 체크 메서드 실행 !");

        int count = 0;
        String openTag = "<div>";
        String closeTag = "</div>";

        List<String> result = new ArrayList<>();

        for ( String contentText : contentTexts) {

            System.out.println("현재 검사중인 라인 : " + contentText);
            System.out.println("현재 검사기 카운트 숫자 : " + count);

            if ( removeHTMLTag(contentText).startsWith("% ") || contentText.startsWith("% ") && count < 3 ) {

                System.out.println("!! div 라인작업 필요 : " + contentText);

                if ( count == 0 ) {
                    result.add(openTag); // 시작 태그 더하기
                    String context = removeHTMLTag(contentText).split("% ")[1].trim();

                    //String context = contentText.split("% 0")[1].trim();
                    contentText = "<p>" + context.trim() + "</p>";
                    result.add(contentText);
                    count++;
                    System.out.println("현재 검사기 카운트 숫자 : " + count);
                    continue;
                }

                if ( count == 1 ) {

                    result.add(closeTag);
                    result.add(openTag);
                    String context = removeHTMLTag(contentText).split("% ")[1].trim();

                    //String context = contentText.split("% 0")[1].trim();
                    contentText = "<p>" + context.trim() + "</p>";
                    result.add(contentText);
                    count++;
                    System.out.println("현재 검사기 카운트 숫자 : " + count);
                    continue;
                }

                System.out.println("현재 검사기 카운트 숫자 : " + count);
            }

            if ( contentText.startsWith("%") || removeHTMLTag(contentText).startsWith("%") && count < 3 ) {

                if ( count == 0 ) {

                    contentText = openTag;
                    result.add(contentText.trim());
                    count++;
                    continue;
                }

                if ( count == 1 ) {

                    contentText = openTag;
                    result.add(closeTag);
                    result.add(contentText.trim());
                    count++;
                    continue;
                }

            }

            if ( contentText.equals("</section>") ) {
                result.add(closeTag);
                result.add(contentText);
                continue;
            }

            result.add(contentText);

        }

        return result;
    }

}
