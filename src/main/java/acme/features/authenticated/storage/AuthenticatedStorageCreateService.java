
package acme.features.authenticated.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.storages.Storage;
import acme.entities.storages.StorageStatus;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedStorageCreateService implements AbstractCreateService<Authenticated, Storage> {

	@Autowired
	private AuthenticatedStorageRepository repository;


	@Override
	public boolean authorise(final Request<Storage> request) {
		assert request != null;

		boolean result;
		int numberOfAuthenticated;
		Principal principal = request.getPrincipal();

		numberOfAuthenticated = this.repository.findNumberOfAuditorByUserAccountId(principal.getAccountId());
		result = numberOfAuthenticated < 1;
		return result;
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

		request.unbind(entity, model, "firm", "responsibilityStatement", "status", "authenticated");

	}

	@Override
	public Storage instantiate(final Request<Storage> request) {
		assert request != null;

		Storage result;
		Principal principal;
		int userAccountId;
		Authenticated authenticated;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		authenticated = this.repository.findAuthenticatedByUserAccountId(userAccountId);

		result = new Storage();
		result.setAuthenticated(authenticated);
		result.setUserAccount(authenticated.getUserAccount());
		result.setStatus(StorageStatus.PENDING);

		return result;
	}

	@Override
	public void validate(final Request<Storage> request, final Storage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		errors.state(request, !(this.repository.numberOfStoragePendingByUserAccountId(userAccountId) > 0), "responsibilityStatement", "authenticated.storage.error.pending");

	}

	@Override
	public void create(final Request<Storage> request, final Storage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
