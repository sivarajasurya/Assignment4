import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.LoggerFactory;
public class Country {
    static Map<String,String> find = new HashMap<>();
    static org.slf4j.Logger logger = LoggerFactory.getLogger(Country.class);
    static Scanner get = new Scanner(System.in);
    static JSONParser parser = new JSONParser();
    public static void main(String[] args) {

        try {
            Object obj = parser.parse(new FileReader(".\\jason\\countries.json"));
            JSONArray countryArray = (JSONArray) obj;

            countryArray.forEach( obj1 -> parseCountryObject( (JSONObject) obj1 ) );


        } catch (Exception e) {
            logger.error("An exception occurred!"+e);
        }

        System.out.println("Enter whether you want to find the country name or by using country code \n 1 for Find using country code \n 2 Find using Country name \n");
        int i = get.nextInt();

        if (i==1) {
            System.out.println("Enter the country name \n");
            usingCountryName(get.next().toUpperCase());
        } else if (i==2) {
            usingCountryCode(get.next().toUpperCase());
        }else {
            System.out.println("Enter the country code \n");
            System.out.println("Entered wrong in put ");
        }
    }

    // To store Country name and code in Map
    private static void parseCountryObject(JSONObject emp) {
        JSONObject employeeObject = (JSONObject) emp;
        String name = (String) employeeObject.get("name");
        String code = (String) employeeObject.get("code");
        find.put(name.toUpperCase(),code.toUpperCase());
    }
    public static void usingCountryName(String name){
        String a = find.get(name);
        if (a == null){
            System.out.println("Invalid input");
        }else {
            System.out.println(a);
        }}

    public static void usingCountryCode(String code){
        String a = getKey(find, code);
        if (a == null){
            System.out.println("Invalid input");
        }else {
            System.out.println(a);
        }

    }
    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
