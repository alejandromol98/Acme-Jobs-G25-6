
package acme.features.administrator.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.storages.Storage;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorStorageShowService implements AbstractShowService<Administrator, Storage> {

	@Autowired
	private AdministratorStorageRepository repository;


	@Override
	public boolean authorise(final Request<Storage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Storage> request, final Storage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibilityStatement", "status");
	}

	@Override
	public Storage findOne(final Request<Storage> request) {
		assert request != null;

		Storage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
