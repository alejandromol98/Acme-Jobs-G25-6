
package acme.features.authenticated.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Job;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/job/")
public class AuthenticatedJobController extends AbstractController<Authenticated, Job> {

	@Autowired
	private AuthenticatedListMineJobListService listMineService;

	@Autowired
	private AuthenticatedJobShowService showService;

	// Constructors -------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
//		super.addBasicCommand(BasicCommand.LIST, this.listMineService);
		 super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST,
		 this.listMineService);

	}

}
