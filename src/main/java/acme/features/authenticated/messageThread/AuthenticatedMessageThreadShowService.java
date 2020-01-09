
package acme.features.authenticated.messageThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.persons.Person;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		boolean result = false;
		int messageThreadId;
		Person person;
		Principal principal;

		messageThreadId = request.getModel().getInteger("id");
		principal = request.getPrincipal();
		person = this.repository.findPersons(messageThreadId, principal.getActiveRoleId());

		result = person != null;
		if (result) {
			request.getServletRequest().setAttribute("author", person.isAuthor());
		}

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "id");

	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {

		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageThreadById(id);

		return result;
	}

}
