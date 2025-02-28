package miu.edu.demo.utils;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.NumberPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PredicatesBuilder {
    private List<SearchCriteria> params;
    private Class<? extends Object> entity;
    private String variable;
    private BooleanExpression initialExp;

    private static String DUMMY1 = "DUMMY1";
    private static String DUMMY2 = "DUMMY2";

    public PredicatesBuilder(Class<? extends Object> entity, String variable) {
        params = new ArrayList<>();
        this.entity = entity;
        this.variable = variable;
    }

    public PredicatesBuilder addParam(
            String key, String operation, Object value) {
        if (value == null) {
            value = "";
        }

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return initializeExp();
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {
            Predicate predicate = new Predicate(param);
            return predicate.getPredicate(entity, variable);
        }).filter(Objects::nonNull).collect(Collectors.toList());

        BooleanExpression result = initializeExp();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        resetParams();
        return result;
    }

    public BooleanExpression build(String search) {
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(=|<|>)([\\w,]+?)&");
            Matcher matcher = pattern.matcher(search);
            while (matcher.find()) {
                this.addParam(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        return build();
    }

    public BooleanExpression initializeExp() {
        //since you can't insert null into findAll, gibberish is inserted instead
        PathBuilder<Class<? extends Object>> entityPath = new PathBuilder(entity, variable);
        NumberPath<Integer> path = entityPath.getNumber("id", Integer.class);
        return path.isNotNull();
    }

    public BooleanExpression initializeExpForOr() {
        PathBuilder<Class<? extends Object>> entityPath = new PathBuilder(entity, variable);
        NumberPath<Integer> path = entityPath.getNumber("id", Integer.class);
        return path.eq(0);
    }

    public void resetParams() {
        params = new ArrayList<>();
    }

    public BooleanExpression buildWithOr() {
        if (params.size() == 0) {
            return initializeExpForOr();
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {
            Predicate predicate = new Predicate(param);
            return predicate.getPredicate(entity, variable);
        }).filter(Objects::nonNull).collect(Collectors.toList());

        BooleanExpression result = initializeExpForOr();
        for (BooleanExpression predicate : predicates) {
            result = result.or(predicate);
        }
        resetParams();
        return result;
    }
}
