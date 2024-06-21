package tech.csm.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import tech.csm.domain.Patient;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public Patient getPatientDetails(String phoneNo) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("doctor_pen_proc")
				.returningResultSet("patientDtls", new BeanPropertyRowMapper(Patient.class));

		Map<String, Object> data = simpleJdbcCall.execute("sepatientbyphone", phoneNo, Types.NULL,Types.NULL,Types.NULL);
		List<Patient> patientData = (List<Patient>) data.get("patientDtls");

		if (patientData.size() > 0)
			return patientData.get(0);
		
		return null;

	}

	@Override
	public Patient getPatientDetails(Integer pId) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("doctor_pen_proc")
				.returningResultSet("patientDtls", new BeanPropertyRowMapper(Patient.class));

		Map<String, Object> data = simpleJdbcCall.execute("sepatientbyId", Types.NULL, pId,Types.NULL,Types.NULL);
		List<Patient> patientData = (List<Patient>) data.get("patientDtls");

		if (patientData.size() > 0)
			return patientData.get(0);
		
		return null;
	}

	

}
