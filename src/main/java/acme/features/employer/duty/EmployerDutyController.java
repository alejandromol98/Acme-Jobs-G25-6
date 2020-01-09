
package acme.features.employer.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/duty/")
public class EmployerDutyController extends AbstractController<Employer, Duty> {

	@Autowired
	private EmployerDutyListService listService;

	@Autowired
	private EmployerDutyShowService showService;

	@Autowired
	private EmployerDutyCreateService createService;

	@Autowired
	private EmployerDutyDeleteService deleteService;

	// Constructors -------------------------------------------

	@PostConstruct
	private void initialise() {
		// super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST,
		// this.listMineService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, createService);
		super.addBasicCommand(BasicCommand.DELETE, deleteService);
	}
}
