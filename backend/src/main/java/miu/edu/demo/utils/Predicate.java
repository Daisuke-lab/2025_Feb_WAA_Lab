package miu.edu.demo.utils;





import com.querydsl.core.types.dsl.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Predicate {

    private SearchCriteria criteria;
    public Predicate (SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate(Class<? extends Object> entity, String variable) {
        PathBuilder<Class<? extends Object>> entityPath = new PathBuilder(entity, variable);
        if (isArray(criteria.getValue().toString())) {
            String[] items = criteria.getValue().toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replaceAll(" ", "")
                    .split(",");
            ArrayPath arrayPath;
            if (isInteger(items[0])) {
                ArrayList<Integer> nums = new ArrayList<>();
                for(String s : items) nums.add(Integer.valueOf(s));
                arrayPath = entityPath.getArray(criteria.getKey(), Integer.class);
                return arrayPath.in(nums);
            } else {
                arrayPath = entityPath.getArray(criteria.getKey(), String.class);
                return arrayPath.in(items);
            }
        } else if (isInteger(criteria.getValue().toString()) || criteria.getValue() instanceof Number) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case "=":
                    return path.eq(value);
                case ">":
                    return path.gt(value);
                case "<":
                    return path.lt(value);
            }

        } else if (isBoolean(criteria.getValue().toString())) {
            BooleanPath path = entityPath.getBoolean(criteria.getKey());
            boolean value = Boolean.parseBoolean(criteria.getValue().toString());
            if (criteria.getOperation().equalsIgnoreCase("=")) {
                return path.eq(value);
            }
        }
        else {
            StringPath path = entityPath.getString(criteria.getKey());
            String value = criteria.getValue().toString();
            switch(criteria.getOperation()) {
                case "=":
                    return path.eq(value);
                case "__contains__":
                    return path.stringValue().contains(value);

            }
        }
        return null;
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public Boolean isBoolean(String value) {
        return "true".equals(value) || "false".equals(value);
    }

    public Boolean isArray(String value) {
        return value.contains(",") || (value.contains("[") && value.contains("]"));}
}
