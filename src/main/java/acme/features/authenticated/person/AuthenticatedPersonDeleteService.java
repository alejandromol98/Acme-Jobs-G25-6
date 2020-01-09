
package acme.features.authenticated.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.persons.Person;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedPersonDeleteService implements AbstractDeleteService<Authenticated, Person> {

	@Autowired
	AuthenticatedPersonRepository repository;


	@Override
	public boolean authorise(final Request<Person> request) {
		assert request != null;

		Person person;
		person = this.repository.findPersonInThread(this.repository.findPersonById(request.getModel().getInteger("id")).getMessageThread().getId(), request.getPrincipal().getActiveRoleId());

		return person.isAuthor();
	}

	@Override
	public void bind(final Request<Person> request, final Person entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Person> request, final Person entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated", "thread");

	}

	@Override
	public Person findOne(final Request<Person> request) {
		assert request != null;

		Person person;
		int id;

		id = request.getModel().getInteger("id");
		person = this.repository.findPersonById(id);
		return person;
	}

	@Override
	public void validate(final Request<Person> request, final Person entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Person> request, final Person entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
