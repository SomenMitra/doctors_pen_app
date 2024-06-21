package tech.csm.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Patient implements Serializable {

	private Integer patientId;
	
	private String patientName;
	
	private String gender;
	
	private Date dob;
	
	private Integer age;
	
	private String phoneNo;
}
