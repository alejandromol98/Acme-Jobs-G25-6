
package acme.entities.offers;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer extends DomainEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^O[a-zA-Z]{4}-\\d{5}$")
	private String				ticker;

	@NotBlank
	private String				title;

	@NotNull
	private Money				min;

	@NotNull
	private Money				max;

	@NotBlank
	@Column(length = 1024)
	private String				text;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

}
