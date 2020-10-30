<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
        <script type="text/javascript">

            function validateStudenDetails(){  
            	var name=document.studentDetailForm.name.value;  
            	var age=document.studentDetailForm.age.value;  
            	  
            	if (name==null || name=="" || !isNaN(name)){  
            	  alert("Name can't have numeric values"); 
            	  return false;  
            	}else if(age==null || age=="" || age<0 || isNaN(age)){  
            	  alert("age must be a number and >0");  
            	  return false; 
            	  }
          	  else{return true;  
            	  }  
            }
            
            
        
            function setSelectedIndex(s, valsearch){
	            for (i = 0; i< s.options.length; i++){ 
	
		            if (s.options[i].value == valsearch){
		            s.options[i].selected = true;
		            break;
		            }
		         }
            return;}

        </script>
        
        </head>


<body>


<a href="<%=request.getContextPath()%>/list">Students List</a>
	<br>
				<c:if test="${student != null}">
					<form action="update" method="post" name="studentDetailForm" onsubmit="return validateStudenDetails(this);" >
				</c:if>
				
				
				<c:if test="${student == null}">
					<form action="insert" method="post" name="studentDetailForm" onsubmit="return validateStudenDetails(this);">
				</c:if>


					<h2>
						<c:if test="${student != null}">
            			<h1>Edit User</h1>
            		</c:if>
						<c:if test="${student == null}">
            			<h1>Add New User</h1>
            		</c:if>
					</h2>

				<c:if test="${student != null}">
					<input type="hidden" name="id" value="<c:out value='${student.id}' />" />
				</c:if>


					<label>Student Name</label> <input type="text"
						value="<c:out value='${student.name}' />" name="name" id="name" required="required">


					<label>Student Age</label> <input type="text"
						value="<c:out value='${student.age}' />" name="age" id="age" required="required">

				
										

					<label>Student branch</label>	
					  <select id="branch" name="branch">
					     <option value="null">---Select Branch---</option>
					     <option value="CSE">CSE</option>
					    <option value="ECE">ECE</option>
					    <option value="EEE">EEE</option>
					    <option value="IT">IT</option>
					  </select>
					  

					  <c:if test="${student != null}">
					 
						   <script type="text/javascript"> 
						   setSelectedIndex(document.getElementById("branch"),"<c:out value='${student.branch}' />");			        
						   </script>
						    
						</c:if>			  
					<label>Student Section</label>	


					  <select id="section" name="section">
					     <option  value="null">---Select Section---</option>
					     <option value="A">A</option>
					    <option value="B">B</option>
					    <option value="C">C</option>
					    <option value="D">D</option>
					  </select>
				


					  <c:if test="${student != null}">
					 
						   <script type="text/javascript"> 
						   setSelectedIndex(document.getElementById("section"),"<c:out value='${student.section}' />");			        
						   </script>
						    
						</c:if>
				<input type="submit"  value="Save">
				</form>
	
</body>



</html>