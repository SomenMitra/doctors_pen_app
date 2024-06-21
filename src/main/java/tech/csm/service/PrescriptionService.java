package tech.csm.service;

import java.util.List;

import tech.csm.domain.Prescription;

public interface PrescriptionService {

	List<Prescription> getPrescriptionDetailsByPatientId(Integer patientId);

	String savePrescription(Prescription presp);

}
