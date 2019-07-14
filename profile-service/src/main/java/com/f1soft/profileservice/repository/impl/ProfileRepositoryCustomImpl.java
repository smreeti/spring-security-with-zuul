package com.f1soft.profileservice.repository.impl;

import com.f1soft.profileservice.exceptions.NoContentFoundException;
import com.f1soft.profileservice.repository.ProfileRepositoryCustom;
import com.f1soft.profileservice.requestDTO.ProfileDTO;
import com.f1soft.profileservice.responseDTO.ProfileMinimalResponseDTO;
import com.f1soft.profileservice.utility.ProfileUtils;
import com.f1soft.profileservice.utility.QueryCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.f1soft.profileservice.constants.ErrorMessageConstants.NoRecordsFound;
import static com.f1soft.profileservice.utility.ProfileUtils.*;

/**
 * @author smriti on 7/10/19
 */
@Service
@Transactional
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProfileMinimalResponseDTO> searchProfile(ProfileDTO profileDTO) {

        Query query = entityManager.createNativeQuery(QueryCreator.createQueryToSearchProfile.apply(profileDTO));

        List<Object[]> results = query.getResultList();

        validateResults.accept(results);

        return results.stream().map(convertObjectToProfileResponseDTO).collect(Collectors.toList());
    }

    public Consumer<List<Object[]>> validateResults = (results -> {
        if (ObjectUtils.isEmpty(results))
            throw new NoContentFoundException(NoRecordsFound.MESSAGE, NoRecordsFound.DEVELOPER_MESSAGE);
    });
}
