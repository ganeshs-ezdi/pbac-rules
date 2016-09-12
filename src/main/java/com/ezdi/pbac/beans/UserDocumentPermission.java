package com.ezdi.pbac.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
@Entity
@Table(name="user_permissions_on_document")
public class UserDocumentPermission implements Serializable {
    @Id
    private int id;
    private int userId;
    private String patientClass;
    private String patientType;
    private String serviceLine;
    private String pointOfCare;
    private String permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UserDocumentPermission{" +
                "id=" + id +
                ", userId=" + userId +
                ", patientClass='" + patientClass + '\'' +
                ", patientType='" + patientType + '\'' +
                ", serviceLine='" + serviceLine + '\'' +
                ", pointOfCare='" + pointOfCare + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
