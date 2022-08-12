package org.omnivor.opinionatedresume.utilities;

public class StringUtils {
    public static String urlPrettyPrint(String url) {
        return url
                .replace("https://", "")
                .replace("www.", "");
    }
}
