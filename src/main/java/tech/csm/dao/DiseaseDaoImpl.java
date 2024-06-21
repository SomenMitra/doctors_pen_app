package tech.csm.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import tech.csm.domain.Disease;
import tech.csm.domain.Patient;
@Repository
public class DiseaseDaoImpl implements DiseaseDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Disease> getAllDisease() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("doctor_pen_proc")
				.returningResultSet("diseaseDtls", new BeanPropertyRowMapper(Disease.class));

		Map<String, Object> data = simpleJdbcCall.execute("sedieses", Types.NULL, Types.NULL,Types.NULL,Types.NULL);
		List<Disease> diseaseList = (List<Disease>) data.get("diseaseDtls");

		return diseaseList;
		
	}

}
