
package acme.entities.investors;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Investor extends DomainEntity {

	//Serialisation indetifier ------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				statement;

	@NotNull
	@Range(min = 0, max = 5)
	private Integer				stars;
}
