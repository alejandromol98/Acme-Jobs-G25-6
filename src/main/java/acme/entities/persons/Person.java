
package acme.entities.persons;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	private boolean				author;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		authenticated;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private MessageThread		messageThread;
}
