package myfood.framework;

import java.util.List;
import java.util.Properties;

public class Outputer<T extends Properties> {
    public String toString(T props, List<String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            sb.append(props.getProperty(field));
            if (i < fields.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString(List<T> list, List<String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("{[");
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            sb.append(toString(item, fields));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
