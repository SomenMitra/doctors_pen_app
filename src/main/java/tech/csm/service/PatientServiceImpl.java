package tech.csm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.PatientDao;
import tech.csm.domain.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;
	
	@Override
	public Patient getPatientDetails(String phoneNo) {
		
		return patientDao.getPatientDetails(phoneNo);
	}

	@Override
	public Patient getPatientDetails(Integer pId) {
		
		return patientDao.getPatientDetails( pId);
	}

	

}
