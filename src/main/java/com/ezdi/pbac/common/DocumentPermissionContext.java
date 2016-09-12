package com.ezdi.pbac.common;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
public class DocumentPermissionContext {
    private String patientClass;
    private String patientType;
    private String serviceLine;
    private String pointOfCare;

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

    public String getServiceLine() {
        return serviceLine;
    }

    public void setServiceLine(String serviceLine) {
        this.serviceLine = serviceLine;
    }

    public String getPointOfCare() {
        return pointOfCare;
    }

    public void setPointOfCare(String pointOfCare) {
        this.pointOfCare = pointOfCare;
    }

    @Override
    public String toString() {
        return "DocumentPermissionContext{" +
                "patientClass='" + patientClass + '\'' +
                ", patientType='" + patientType + '\'' +
                ", serviceLine='" + serviceLine + '\'' +
                ", pointOfCare='" + pointOfCare + '\'' +
                '}';
    }
}
