package utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzz
 * @date 2019/7/12 17:55
 **/

public class Utils {

    public static String updateString(String content) {
        char[] chars = content.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '_') {
                i++;
                sb.append(new String(chars, i, 1).toUpperCase());
            } else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static String toCamel(String content) {
        String[] ss = content.split(",");
        List<String> finalList = new LinkedList<>();
        for (String s : ss) {
            String cameled = updateString(s);
            finalList.add(s + " as" + cameled);
        }
        return String.join(",", finalList);
    }
}
