package tech.csm.dao;

import java.util.List;

import tech.csm.domain.Prescription;

public interface PrescriptionDao {

	List<Prescription> getPrescriptionDetailsByPatientId(Integer patientId);

	String savePrescription(Prescription presp);

}
