// valiadation scriptsnn
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

