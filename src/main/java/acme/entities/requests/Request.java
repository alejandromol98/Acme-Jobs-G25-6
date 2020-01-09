
package acme.entities.requests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Request extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotNull
	private Money				reward;

	@Pattern(regexp = "^R[a-zA-Z]{4}-\\d{5}$")
	@Column(unique = true)
	private String				ticker;

	@Pattern(regexp = "\\+\\d{1,3}\\s\\(\\d{1,4}\\)\\s\\d{6,10}")
	private String				telephone;

	@NotNull
	@Range(min = 0, max = 5)
	private Integer				stars;
}
