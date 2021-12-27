package com.exarep.customer.individual;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerIndividualRepository implements PanacheRepositoryBase<CustomerIndividualEntity, Integer> {
}
