package acme.features.anonymous.curriculum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.curriculums.Curriculum;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/curriculum/")
public class AnonymousCurriculumController extends AbstractController<Anonymous, Curriculum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousCurriculumListService listService;

	@Autowired
	private AnonymousCurriculumCreateService createService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}

}
