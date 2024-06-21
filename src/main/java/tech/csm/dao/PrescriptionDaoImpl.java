package tech.csm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import tech.csm.domain.Disease;
import tech.csm.domain.Patient;
import tech.csm.domain.Prescription;


@Repository
public class PrescriptionDaoImpl implements PrescriptionDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Prescription> getPrescriptionDetailsByPatientId(Integer patientId) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("doctor_pen_proc")
				.returningResultSet("prescriptionHistoryDtls", new RowMapper<Prescription>() {

					@Override
					public Prescription mapRow(ResultSet rs, int rowNum) throws SQLException {
						Prescription prescription=new Prescription();
						prescription.setPrescriptionId(rs.getInt(1));
						prescription.setDoctorName(rs.getString(2));
						prescription.setDateOfVisit(rs.getDate(3));
						prescription.setPrescriptionDetails(rs.getString(4));
						Patient p=new Patient();
						p.setPatientId(rs.getInt(5));
						p.setPatientName(rs.getString(6));
						p.setGender(rs.getString(7));
						p.setDob(rs.getDate(8));
						p.setPhoneNo(rs.getString(9));
						prescription.setPatient(p);
						Disease d=new Disease();
						d.setDiseaseId(rs.getInt(10));
						d.setDiseaseName(rs.getString(11));
						prescription.setDisease(d);
						
						
						return prescription;
					}
				});

		Map<String, Object> data = simpleJdbcCall.execute("sepatienthistory", Types.NULL, patientId,Types.NULL,Types.NULL);
		List<Prescription> prescriptionData = (List<Prescription>) data.get("prescriptionHistoryDtls");

		
		
		return prescriptionData;
	}

	@Override
	public String savePrescription(Prescription presp) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("doctor_pen_proc");


		Map<String, Object> res = simpleJdbcCall.execute("inprescription",Types.NULL, presp.getPatient().getPatientId(), presp.getDisease().getDiseaseId(),presp.getPrescriptionDetails());

		return (String) res.get("p_msg");
	}

}
