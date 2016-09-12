package com.ezdi.pbac.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
@Entity
@Table(name="user_permissions_on_encounter")
public class UserEncounterPermission implements Serializable {
    @Id
    private int id;
    private int userId;
    private String patientClass;
    private String patientType;
    private String financialClass;
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

    public String getFinancialClass() {
        return financialClass;
    }

    public void setFinancialClass(String financialClass) {
        this.financialClass = financialClass;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UserEncounterPermission{" +
                "id=" + id +
                ", userId=" + userId +
                ", patientClass='" + patientClass + '\'' +
                ", patientType='" + patientType + '\'' +
                ", financialClass='" + financialClass + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
