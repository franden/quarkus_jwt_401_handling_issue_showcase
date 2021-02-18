package org.acme.rest.control;

public class JsonUtils {
    public static <T> T firstNotNull(T first, T second) {
        if (first == null) {
            return second;
        } else {
            return first;
        }
    }
}
