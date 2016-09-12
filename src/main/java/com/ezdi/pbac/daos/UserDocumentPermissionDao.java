package com.ezdi.pbac.daos;

import com.ezdi.pbac.beans.UserDocumentPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
public interface UserDocumentPermissionDao extends CrudRepository<UserDocumentPermission, Integer> {
    public static final String documentPermissionQuery = "select p\n" +
            "from UserDocumentPermission p\n" +
            "where p.userId = :userId\n" +
            "\tAND (p.patientClass = :patientClass OR p.patientClass = '*')\n" +
            "\tAND (p.patientType = :patientType OR p.patientType = '*')\n" +
            "\tAND (p.serviceLine = :serviceLine OR p.serviceLine = '*')\n" +
            "\tAND (p.pointOfCare = :pointOfCare OR p.pointOfCare = '*')";

    @Query(documentPermissionQuery)
    public List<UserDocumentPermission> getDocumentPermissions(
            @Param("userId") int userId,
            @Param("patientClass") String patientClass,
            @Param("patientType") String patientType,
            @Param("serviceLine") String serviceLine,
            @Param("pointOfCare") String pointOfCare
    );

    public List<UserDocumentPermission> findByUserId(int userId);
}
