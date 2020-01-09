
package acme.features.administrator.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.storages.Storage;
import acme.entities.storages.StorageStatus;
import acme.features.administrator.auditor.AdministratorAuditorCreateService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorStorageUpdateService implements AbstractUpdateService<Administrator, Storage> {

	@Autowired
	AdministratorStorageRepository				repository;

	@Autowired
	private AdministratorAuditorCreateService	storage;


	@Override
	public boolean authorise(final Request<Storage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Storage> request, final Storage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
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

	@Override
	public void validate(final Request<Storage> request, final Storage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Storage> request, final Storage entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals(StorageStatus.ACCEPTED)) {
			this.storage.createFromStorage(entity);
		}
		this.repository.save(entity);
	}

}
