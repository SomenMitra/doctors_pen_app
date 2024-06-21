package tech.csm.service;

import java.util.List;
import java.util.Map;

import tech.csm.domain.Patient;

public interface PatientService {

	Patient getPatientDetails(String phoneNo);

	Patient getPatientDetails(Integer pId);

	

}
