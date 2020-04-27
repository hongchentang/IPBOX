<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div width="600px">
	<iframe id="pdfView" src="" width="100%" height="650px">
		<!-- This browser does not support PDFs. Please download the PDF to view it: <a href="./cn85100005.pdf">Download PDF</a> -->
	</iframe>
</div>
<script type="text/javascript">
	$(document).ready(function (){
// 	var obj = ${patentPDF};
//  	alert(obj._id);
//  	var newstr=JSON.stringify(obj); //返回一个新字符串
//  	console.log(newstr);
	var application_number = "${patentPDF.application_number}";
	var id = "${patentPDF._id}"  ;
	var data= "${patentPDF.earliest_publication_date}";
	var pathDir = "\/"+data.substring(0,4)+"\/"+data+"\/"+id.substring(3,8)+"\/"+application_number+".pdf";
// 	$('#pdfView').attr('src', pathDir);
	$('#pdfView').attr('src', '/upload/cms/pdf/cn85100005.pdf');
});

</script>
