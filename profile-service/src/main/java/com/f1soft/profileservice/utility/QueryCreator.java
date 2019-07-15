package com.f1soft.profileservice.utility;

import com.f1soft.profileservice.requestDTO.ProfileDTO;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author smriti on 7/9/19
 */
public class QueryCreator {

    public static Function<ProfileDTO, String> createQueryToSearchProfile = (profileDTO -> {
        String query = "";

        query += " SELECT" +
                " p.id," +                          //[0]
                " p.name," +                        //[1]
                " p.status," +                      //[2]
                " p.department_id," +               //[3]
                " p.sub_department_id" +            //[4]
                " FROM" +
                " profile p" +
                " WHERE p.id!=0";

        if (!Objects.isNull(profileDTO.getName()))
            query += " AND p.name='" + profileDTO.getName() + "'";

        if (!Objects.isNull(profileDTO.getDepartmentId()))
            query += " AND p.department_id=" + profileDTO.getDepartmentId();

        if (!Objects.isNull(profileDTO.getSubDepartmentId()))
            query += " AND p.sub_department_id=" + profileDTO.getDepartmentId();

        query += " ORDER BY p.id DESC";
        return query;
    });

    public static Function<Long, String> createQueryToFetchAllProfileDetails = (id) -> {
        return "SELECT" +
                " pm.id," +                 //[0]
                " pm.profile_id," +         //[1]
                " pm.role_id, " +           //[2]
                " pm.user_menu_id, " +      //[3]
                " p.description" +          //[4]
                " FROM profile p" +
                " LEFT JOIN profile_menu pm ON pm.profile_id = p.id" +
                " WHERE" +
                " p.id = " + id;
    };

}
