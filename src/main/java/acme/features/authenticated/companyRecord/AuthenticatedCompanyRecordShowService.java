
package acme.features.authenticated.companyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedCompanyRecordShowService implements AbstractShowService<Authenticated, CompanyRecord> {

	@Autowired
	private AuthenticatedCompanyRecordRepository repository;


	// Interfaz de Abstract ShowService

	@Override
	public boolean authorise(final Request<CompanyRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecord> request, final CompanyRecord entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "CEO", "sector", "website", "description", "email", "telephone", "incorporated");
	}

	@Override
	public CompanyRecord findOne(final Request<CompanyRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;

		CompanyRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		if (result.isIncorporated() == true) {
			String res = "";

			res = result.getName() + " Inc";
			result.setName(res);
		} else {
			String res = "";
			res = result.getName() + " Llc";
			result.setName(res);
		}
		return result;
	}

}
