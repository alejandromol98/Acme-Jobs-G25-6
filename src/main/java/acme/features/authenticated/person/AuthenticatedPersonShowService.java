
package acme.features.authenticated.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.persons.Person;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedPersonShowService implements AbstractShowService<Authenticated, Person> {

	@Autowired
	AuthenticatedPersonRepository repository;


	@Override
	public boolean authorise(final Request<Person> request) {
		assert request != null;

		Person person;
		MessageThread messageThread;

		messageThread = this.repository.findPersonById(request.getModel().getInteger("id")).getMessageThread();
		person = this.repository.findPersonInThread(messageThread.getId(), request.getPrincipal().getActiveRoleId());

		return person.isAuthor();
	}

	@Override
	public void unbind(final Request<Person> request, final Person entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated.userAccount.username", "messageThread.title");
	}

	@Override
	public Person findOne(final Request<Person> request) {
		assert request != null;

		Person result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPersonById(id);

		return result;
	}

}
