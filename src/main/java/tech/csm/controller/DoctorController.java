package tech.csm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tech.csm.domain.Disease;
import tech.csm.domain.Patient;
import tech.csm.domain.Prescription;
import tech.csm.service.DiseaseService;
import tech.csm.service.PatientService;
import tech.csm.service.PrescriptionService;

@Controller
public class DoctorController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private PrescriptionService prescriptionService;

	@GetMapping("/getForm")
	public String getForm(Model model) {

		return "getForm";
	}

	@GetMapping("/getPatientDetails")
	public String getPatientDetails(@RequestParam("pphoneno") String phoneNo, Model model) {

		Patient patient = patientService.getPatientDetails(phoneNo);
		if (patient != null) {
			model.addAttribute("patientDtls", patient);
			List<Disease> dList = diseaseService.getAllDisease();
			model.addAttribute("dList", dList);
//			List<Prescription> presList = prescriptionService.getPrescriptionDetailsByPatientId(patient.getPatientId());
//			model.addAttribute("presList", presList);
		}
		return "getForm";
	}

	@PostMapping("/savePrecriptionDetails")
	public String savePrecriptionDetails(@RequestParam Integer patientId, @RequestParam Integer dieseaseNameId,
			@RequestParam String presDtls, @RequestParam String pphoneno, RedirectAttributes redirectAttributes) {
		System.out.println(patientId + "  " + dieseaseNameId + "  " + presDtls + " " + pphoneno);

		Prescription presp = new Prescription();
		Patient pt = new Patient();
		pt.setPatientId(patientId);
		presp.setPatient(pt);
		
		Disease di = new Disease();
		di.setDiseaseId(dieseaseNameId);
		presp.setDisease(di);
		
		presp.setPrescriptionDetails(presDtls);
		
		String msg = prescriptionService.savePrescription(presp);
		
		redirectAttributes.addAttribute("pId", patientId);
		return "redirect:./getDetailPrescription";

	}

	@GetMapping("/getDetailPrescription")
	public String getDetailPrescription(Model model,@RequestParam("pId") Integer pId) {
		Patient patient = patientService.getPatientDetails(pId);
		if (patient != null) {
			model.addAttribute("patientDtls", patient);
			List<Disease> dList = diseaseService.getAllDisease();
			model.addAttribute("dList", dList);
			List<Prescription> presList = prescriptionService.getPrescriptionDetailsByPatientId(patient.getPatientId());
			
			presList.forEach(p -> {
				LocalDate dob = LocalDate.parse(p.getPatient().getDob()+"");
				LocalDate currDate = LocalDate.now();
				Period period = Period.between(dob, currDate);
				p.getPatient().setAge(period.getYears());
			});
			
			model.addAttribute("presList", presList);
		}
		
		return "getForm";
	}

}
