package acme.features.administrator.companyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCompanyRecordUpdateService implements AbstractUpdateService<Administrator, CompanyRecord> {

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

		request.bind(entity, errors, "name");
	}

	@Override
	public void unbind(Request<CompanyRecord> request, CompanyRecord entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "sector", "CEO", "description", "website", "telephone", "email", "incorporated");
	}

	@Override
	public CompanyRecord findOne(Request<CompanyRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;

		CompanyRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

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
	public void update(Request<CompanyRecord> request, CompanyRecord entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
