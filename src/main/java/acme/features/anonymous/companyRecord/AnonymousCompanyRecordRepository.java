package acme.features.anonymous.companyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousCompanyRecordRepository extends AbstractRepository{

	
	@Query("select r from CompanyRecord r where r.id = ?1")
	CompanyRecord findOneById(int id);
	
	@Query("select r from CompanyRecord r")
	Collection<CompanyRecord> findManyAll();



}
