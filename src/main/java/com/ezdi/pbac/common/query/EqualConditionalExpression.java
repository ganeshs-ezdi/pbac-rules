package com.ezdi.pbac.common.query;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
public class EqualConditionalExpression implements Expression {
    private String variableName;
    private String value;

    public EqualConditionalExpression() {
        variableName = "";
        value = "";
    }

    public EqualConditionalExpression(String variableName, String value) {
        this.variableName = variableName;
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EqualConditionalExpression{" +
                "variableName='" + variableName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public String toSql() {
        return variableName + " = " + value;
    }
}
