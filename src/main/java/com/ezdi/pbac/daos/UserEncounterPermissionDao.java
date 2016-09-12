package com.ezdi.pbac.daos;

import com.ezdi.pbac.beans.UserEncounterPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
public interface UserEncounterPermissionDao extends CrudRepository<UserEncounterPermission, Integer> {
    public static final String documentPermissionQuery = "select p\n" +
            "from UserEncounterPermission p\n" +
            "where p.userId = :userId\n" +
            "\tAND (p.patientClass = :patientClass OR p.patientClass = '*')\n" +
            "\tAND (p.patientType = :patientType OR p.patientType = '*')\n" +
            "\tAND (p.financialClass = :financialClass OR p.financialClass = '*')";

    @Query(documentPermissionQuery)
    public List<UserEncounterPermission> getDocumentPermissions(
            @Param("userId") int userId,
            @Param("patientClass") String patientClass,
            @Param("patientType") String patientType,
            @Param("financialClass") String financialClass
    );
}
