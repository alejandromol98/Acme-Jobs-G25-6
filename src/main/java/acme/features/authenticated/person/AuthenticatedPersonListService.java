
package acme.features.authenticated.person;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.persons.Person;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedPersonListService implements AbstractListService<Authenticated, Person> {

	@Autowired
	AuthenticatedPersonRepository repository;


	@Override
	public boolean authorise(final Request<Person> request) {
		assert request != null;

		Person person;
		person = this.repository.findPersonInThread(request.getModel().getInteger("id"), request.getPrincipal().getActiveRoleId());

		return person.isAuthor();

	}

	@Override
	public void unbind(final Request<Person> request, final Person entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated", "authenticated.userAccount.username", "messageThread", "messageThread.title", "messageThread.id");
	}

	@Override
	public Collection<Person> findMany(final Request<Person> request) {
		assert request != null;

		Collection<Person> persons;

		persons = this.repository.findManyByThreadId(request.getModel().getInteger("id"));

		return persons;

	}

}
