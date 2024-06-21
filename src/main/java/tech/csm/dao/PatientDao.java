package tech.csm.dao;

import java.util.List;
import java.util.Map;

import tech.csm.domain.Patient;

public interface PatientDao {

	Patient getPatientDetails(String phoneNo);

	Patient getPatientDetails(Integer pId);

	

}
