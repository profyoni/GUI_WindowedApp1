import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebScraper {
    public static void main(String[] args) throws Exception {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        String url = "https://www.example.com/";
        HtmlPage page = client.getPage(url);
        String pageContent = page.asNormalizedText();

        System.out.println(pageContent);

        double x = +.03;
    }
}
