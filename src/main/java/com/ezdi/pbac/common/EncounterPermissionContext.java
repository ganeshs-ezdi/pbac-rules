package com.ezdi.pbac.common;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
public class EncounterPermissionContext {
    private String patientClass;
    private String patientType;
    private String financialClass;

    public String getPatientClass() {
        return patientClass;
    }

    public void setPatientClass(String patientClass) {
        this.patientClass = patientClass;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getFinancialClass() {
        return financialClass;
    }

    public void setFinancialClass(String financialClass) {
        this.financialClass = financialClass;
    }

    @Override
    public String toString() {
        return "EncounterPermissionContext{" +
                "patientClass='" + patientClass + '\'' +
                ", patientType='" + patientType + '\'' +
                ", financialClass='" + financialClass + '\'' +
                '}';
    }
}
