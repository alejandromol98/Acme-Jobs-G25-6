
package acme.features.administrator.storage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.storages.Storage;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/storage/")
public class AdministratorStorageController extends AbstractController<Administrator, Storage> {

	@Autowired
	private AdministratorStorageListService		listService;

	@Autowired
	private AdministratorStorageShowService		showService;

	@Autowired
	private AdministratorStorageUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
