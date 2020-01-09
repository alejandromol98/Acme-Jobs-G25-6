
package acme.features.authenticated.person;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.persons.Person;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/person")
public class AuthenticatedPersonController extends AbstractController<Authenticated, Person> {

	@Autowired
	AuthenticatedPersonListService		listService;

	@Autowired
	AuthenticatedPersonShowService		showService;

	@Autowired
	AuthenticatedPersonCreateService	createService;

	@Autowired
	AuthenticatedPersonDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
