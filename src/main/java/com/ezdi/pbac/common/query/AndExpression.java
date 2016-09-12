package com.ezdi.pbac.common.query;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
public class AndExpression implements Expression {
    private List<Expression> andExpressions;

    public AndExpression() {
        this.andExpressions = new ArrayList<>();
    }

    public List<Expression> getAndExpressions() {
        return andExpressions;
    }

    @Override
    public String toSql() {
        ArrayList<String> sqls = new ArrayList<String>();
        for(Expression exp : andExpressions) {
            sqls.add("(" + exp.toSql() + ")");
        }
        return StringUtils.arrayToDelimitedString(sqls.toArray(), " AND ");
    }
}
