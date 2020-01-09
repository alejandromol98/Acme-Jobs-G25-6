package acme.features.anonymous.companyRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCompanyRecordListService implements AbstractListService<Anonymous, CompanyRecord> {

	// Internal state -----------------------------------

	@Autowired
	AnonymousCompanyRecordRepository repository;

	// AbstractShowService<Administrator,RecordCompany> interface------------

	@Override
	public boolean authorise(Request<CompanyRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<CompanyRecord> request, CompanyRecord entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector");
	}

	@Override
	public Collection<CompanyRecord> findMany(Request<CompanyRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Collection<CompanyRecord> result;

		result = this.repository.findManyAll();

		return result;
	}

}
