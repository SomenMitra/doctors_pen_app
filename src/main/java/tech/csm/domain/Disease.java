package tech.csm.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Disease implements Serializable {

	private Integer diseaseId;
	
	private String diseaseName;
}
