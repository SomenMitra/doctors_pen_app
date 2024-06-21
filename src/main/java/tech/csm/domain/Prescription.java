package tech.csm.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Prescription implements Serializable {

	private Integer prescriptionId;
	private String doctorName;
	private Date dateOfVisit;
	private Patient patient;
	private Disease disease;
	private String prescriptionDetails;
	
}
