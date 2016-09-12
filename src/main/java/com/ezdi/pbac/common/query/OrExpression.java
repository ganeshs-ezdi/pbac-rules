package com.ezdi.pbac.common.query;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
public class OrExpression implements Expression {
    private List<Expression> orExpressions;

    public OrExpression() {
        this.orExpressions = new ArrayList<>();
    }

    public List<Expression> getOrExpressions() {
        return orExpressions;
    }

    @Override
    public String toSql() {
        ArrayList<String> sqls = new ArrayList<String>();
        for(Expression exp : orExpressions) {
            sqls.add("(" + exp.toSql() + ")");
        }
        return StringUtils.arrayToDelimitedString(sqls.toArray(), " OR ");
    }
}
