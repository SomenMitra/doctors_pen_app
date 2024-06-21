package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.dao.DiseaseDao;
import tech.csm.domain.Disease;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private DiseaseDao diseaseDao;
	@Override
	public List<Disease> getAllDisease() {
		return diseaseDao.getAllDisease();
	}

}
