<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />

<script src="https://code.jquery.com/jquery-2.2.4.js"
	integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.13/jspdf.plugin.autotable.min.js"></script>

<style>
.history-form {
	border: 2px solid black;
	padding: 20px;
	margin: 20px;
	border-radius: 10px;
}

.history-header {
	text-align: center;
	font-weight: bold;
}

.logo {
	display: inline-block;
	width: 50px;
	height: 50px;
	background: url('your-logo-url-here.png') no-repeat center center;
	background-size: contain;
}

.section {
	border: 2px solid black;
	padding: 10px;
	margin: 10px 0;
	border-radius: 10px;
}

.section h5 {
	font-weight: bold;
}
</style>


</head>
<body>
	<c:if test="${msg ne null}">
		<div class="alert alert-success" id="alId">${msg}</div>
	</c:if>


	<div class="container mt-4">

		<div class="h3 text-primary text-center">Doctor's Pen</div>

		<form action="./getPatientDetails" method="get" id="regdFormId"
			enctype="multipart/form-data">
			<div class="row">
				<div class="col-3">
					<div>
						<label>Patients Phone No</label> <input type="text"
							name="pphoneno" id="pphonenoId" class="form-control">
					</div>
				</div>
				<div class="row mt-3 ">
					<div class="col-md-6">
						<label> </label>
						<button type="submit" class="btn btn-primary">Search</button>

					</div>
				</div>
			</div>

		</form>


		<div class="card mt-4">
			<div class="card-header">
				<h4>Patient Details</h4>
			</div>
			<div class="card-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Patient Name</th>
							<th>Phone NO</th>
							<th>Gender</th>
							<th>DOB</th>
							<th>Patient History</th>
						</tr>
					</thead>
					<tbody id="registrationDetails">
						<c:choose>
							<c:when test="${patientDtls ne null}">
								<tr>
									<td>${patientDtls.patientName}</td>
									<td>${patientDtls.phoneNo}</td>
									<td>${patientDtls.gender}</td>
									<td><fmt:formatDate value="${patientDtls.dob}"
											pattern="dd/MM/yyyy" /></td>
									<td><a class="btn btn-warning"
										href="./getDetailPrescription?pId=${patientDtls.patientId}">view</a></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5">No Data Found</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>




		</div>





		<div class="card mt-4">
			<div class="card-header">
				<h4>Add Prescription Details</h4>
			</div>

			<div class="card-body">


				<form action="./savePrecriptionDetails" method="post"
					id="regdFormId1" enctype="multipart/form-data">
					<input type="hidden" name="patientId"
						value="${patientDtls.patientId }"> <input type="hidden"
						name="pphoneno" value="${patientDtls.phoneNo }">
					<div class="row">
						<div class="col-3">
							<div>
								<label>Diesease Name</label> <select name="dieseaseNameId"
									id="dieseaseNameId" class="form-control">
									<option value="0">-select-</option>
									<c:forEach items="${dList}" var="d">
										<option value="${d.diseaseId}">${d.diseaseName}</option>
									</c:forEach>

								</select>
							</div>
						</div>
						<div class="col-8">
							<div>
								<label>Prescription Deatils</label>
								<textarea rows="3" cols="50" placeholder="Rx." name="presDtls"
									class="form-control"></textarea>

							</div>
						</div>

					</div>
					<div class="row mt-3 ">
						<div class="col-md-6">
							<label> </label>
							<button type="submit" class="btn btn-primary">Submit</button>

						</div>
					</div>
				</form>

			</div>
		</div>
		<c:if test="${presList ne null}">
			<div class="container">
				<div class="history-form">
					<div class="row">
						<div class="col-md-2 text-center">
							<div class="logo"></div>
						</div>
						<div class="col-md-8">
							<div class="history-header">
								<h2>Patient's Medical History</h2>
							</div>
						</div>
					</div>
					<div class="row mt-4">
						<div class="col-md-4">
							<strong>NAME:</strong> <span>${presList[0].patient.patientName}</span>
						</div>
						<div class="col-md-4">
							<strong>AGE:</strong> <span>${presList[0].patient.age}</span>
						</div>
						<div class="col-md-4">
							<strong>GENDER:</strong> <span>${presList[0].patient.gender}</span>
						</div>
					</div>
					<c:forEach items="${presList}" var="p">
						<div class="section mt-4">
							<div class="row">
								<div class="col-md-12">
									<h5>${ p.dateOfVisit }</h5>
								</div>
								<div class="col-md-12">
									<p>
										<strong>Disease Name:</strong>${p.disease.diseaseName }
									</p>
									<p>
										<strong>Prescription Details:</strong>${ p.prescriptionDetails }
									</p>
								</div>
							</div>
						</div>
					</c:forEach>

					<div class="text-center mt-4">
						<p>
							<strong>/** END OF REPORT /**</strong>
						</p>
					</div>
				</div>
			</div>
		</c:if>

	</div>

	<script type="text/javascript">
	
	$(document).ready(function() {
		setTimeout(function() {
			document.getElementById("alId").style.display = 'none';
		}, 5000);
	});
	
	$(function() {
		$("#vTableId").dataTable({
			"lengthMenu" : [ 3, 5, 10, 15, "All" ]
		});
	});
	
	
		function getEmpByBatchandTechId() {
			var technologyId=$("#technologyId").val();
			var batchId=$("#batchId").val();
			console.log(technologyId,batchId);
			$.ajax({
				url : 'getEmpByBatchandTechId',
				type : 'GET',
				data : {
					technologyId : technologyId,
					batchId : batchId
				},

				success : function(response) {
					$("#empNameId").html(response);
				},
				error : function(error) {
					console.log(error)
				}
			});

		}
		
		$('#downloadPdfBtn').click(function() {
			const { jsPDF } = window.jspdf;
			const doc = new jsPDF();
			doc.autoTable({
				html: '#vTableId',
				styles: { fontSize: 8 },
				margin: { top: 20 },
				headStyles: { fillColor: [23, 162, 184] }
			});
			doc.save('batch.pdf');
		});

		function validateForm() {
			if ($("#providerId option:selected").val() == '0') {
				alert("Please select a provider");

				return false;
			}
			if ($("#subscriptionId option:selected").val() == '0') {
				alert("Please select a subscription");

				return false;
			}

			return false;
		}
	</script>
</body>
</html>