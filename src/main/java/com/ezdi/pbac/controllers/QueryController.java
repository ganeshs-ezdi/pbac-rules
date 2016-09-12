package com.ezdi.pbac.controllers;

import com.ezdi.pbac.beans.UserDocumentPermission;
import com.ezdi.pbac.common.query.AndExpression;
import com.ezdi.pbac.common.query.EqualConditionalExpression;
import com.ezdi.pbac.common.query.OrExpression;
import com.ezdi.pbac.daos.UserDocumentPermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by EZDI\ganesh.s on 7/9/16.
 */
@RestController
public class QueryController {
    @Autowired
    private UserDocumentPermissionDao documentPermissionDao;

    @RequestMapping("/query/document/{userId}")
    public String getDocumentWhereCondition(@PathVariable int userId) {
        List<UserDocumentPermission> permissions = documentPermissionDao.findByUserId(userId);
        OrExpression whereCondition = new OrExpression();

        for(UserDocumentPermission permission : permissions) {
            AndExpression expression = getAndExpression(permission);
            whereCondition.getOrExpressions().add(expression);
        }

        return whereCondition.toSql();
    }

    private AndExpression getAndExpression(UserDocumentPermission permission) {
        AndExpression expression = new AndExpression();

        if(!("*".equals(permission.getPatientClass()))){
            expression.getAndExpressions().add(new EqualConditionalExpression("patientClass", "'" + permission.getPatientClass() + "'"));
        }
        if(!("*".equals(permission.getPatientType()))){
            expression.getAndExpressions().add(new EqualConditionalExpression("patientType", "'" + permission.getPatientType() + "'"));
        }
        if(!("*".equals(permission.getServiceLine()))){
            expression.getAndExpressions().add(new EqualConditionalExpression("serviceLine", "'" + permission.getServiceLine() + "'"));
        }
        if(!("*".equals(permission.getPointOfCare()))){
            expression.getAndExpressions().add(new EqualConditionalExpression("pointOfCare", "'" + permission.getPointOfCare() + "'"));
        }

        return expression;
    }
}
