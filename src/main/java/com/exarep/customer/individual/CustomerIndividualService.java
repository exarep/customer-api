package com.exarep.customer.individual;

import com.exarep.customer.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class CustomerIndividualService {

    private final CustomerIndividualRepository repository;
    private final CustomerIndividualMapper mapper;

    public List<CustomerIndividual> findAll(){
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<CustomerIndividual> findById(@NonNull Integer customerId) {
        return repository.findByIdOptional(customerId).map(mapper::toDomain);
    }

    @Transactional
    public void save(@NonNull @Valid CustomerIndividual customer) {
        CustomerIndividualEntity entity = mapper.toEntity(customer);
        repository.persist(entity);
        mapper.toDomain(entity, customer);
    }

    @Transactional
    public void update(@NonNull @Valid CustomerIndividual customer) {
        if (customer.getCustomerIndividualId() == null) {
            throw new ServiceException("CustomerIndividual update failed. CustomerIndividual.customerIndividualId is null.");
        }
        CustomerIndividualEntity entity = repository.findByIdOptional(customer.getCustomerIndividualId())
                .orElseThrow(() -> new ServiceException("CustomerIndividual update failed. No CustomerIndividual found for customerIndividualId[%s].", customer.getCustomerIndividualId()));
        mapper.toEntity(customer, entity);
        repository.persist(entity);
        mapper.toDomain(entity, customer);
    }


}
