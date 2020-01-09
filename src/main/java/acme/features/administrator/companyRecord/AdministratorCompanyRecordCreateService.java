package acme.features.administrator.companyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCompanyRecordCreateService implements AbstractCreateService<Administrator, CompanyRecord> {

	@Autowired
	AdministratorCompanyRecordRepository repository;

	@Override
	public boolean authorise(Request<CompanyRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<CompanyRecord> request, CompanyRecord entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<CompanyRecord> request, CompanyRecord entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name","sector", "CEO", "description", "website", "telephone", "email", "incorporated");
	}

	@Override
	public CompanyRecord instantiate(Request<CompanyRecord> request) {
		// TODO Auto-generated method stub
		CompanyRecord result;
		result = new CompanyRecord();
		return result;
	}

	@Override
	public void validate(Request<CompanyRecord> request, CompanyRecord entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(Request<CompanyRecord> request, CompanyRecord entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
