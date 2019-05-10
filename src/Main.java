import com.google.gson.Gson;
        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import java.io.IOException;
        import java.util.*;  

/**
 * @autor Poul Chuk
 * @version 1.1
 */

public class Main {
    public static void main(String[] args) throws IOException {
        String url="https://www.scalemates.com/kits/news.php"; 
        Document document = Jsoup.connect(url).get();

        List<Article> results = new ArrayList<>();
        Map<String,List<Article>> result = new HashMap<>();
        result.put("results",results);

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите количество товаров: ");
        int n = scan.nextInt();

        for(int i=0;i<Math.ceil(n/100.0) ;i++) {
            results.addAll(ParserLogic.parsing(document, n-i*100));
        }
        
        Gson gson = new Gson();
        String string = gson.toJson(results);
        WriteJSONFile json= new WriteJSONFile();
        json.writeParser(document);
        json.writeToJson(string);
        System.out.println(string.replace("},{","},\n{").replace("\",\"","\",\n\""));
      
    }
}

