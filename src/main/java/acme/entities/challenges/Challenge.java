
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	//Serialisation indetifier ------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	//	@Future
	private Date				deadline;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotNull
	private Money				rewardGoalGold;

	@NotNull
	private Money				rewardGoalSilver;

	@NotNull
	private Money				rewardGoalBronze;
}
