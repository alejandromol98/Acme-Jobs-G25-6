package acme.features.authenticated.request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requests.Request;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/request/")
public class AuthenticatedRequestController extends AbstractController<Authenticated, Request> {

	@Autowired
	private AuthenticatedRequestListService listService;

	@Autowired
	private AuthenticatedRequestShowService showService;

	@Autowired
	private AuthenticatedRequestCreateService createService;

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, listService);
		super.addBasicCommand(BasicCommand.SHOW, showService);
		super.addBasicCommand(BasicCommand.CREATE, createService);
	}
}
