package miu.edu.demo.utils;
import java.util.ArrayList;
import java.util.Map;
public class ParamsConverter {
    public static String convertParamsToString(Map<String, String> map) {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            text.append(entry.getKey());
            if (!entry.getKey().contains(">") & !entry.getKey().contains("<")) {
                text.append("=");
            }
            text.append(entry.getValue());
            text.append("&");
        }
        return text.toString();
    }

    public static String[] convertStringToArray(String text) {
        return text.split(",");
    }
}
