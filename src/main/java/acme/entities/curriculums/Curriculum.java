package acme.entities.curriculums;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Curriculum extends DomainEntity {

	// Serialisation indetifier ------------------------

	private static final long serialVersionUID = 1L;

	// Atributes ---------------------------------------

	@NotBlank
	private String name;

	@NotBlank
	private String skills;

	@NotBlank
	private String studies;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date moment;
}