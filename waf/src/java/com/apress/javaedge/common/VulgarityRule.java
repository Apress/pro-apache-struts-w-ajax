/*
 * VulgarityRule.java
 *
 */

package com.apress.javaedge.common;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.struts.action.ActionErrors;
//import org.apache.struts.util.StrutsValidatorUtil;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;


/**
 * @author jcarnell
 *
 * The VulgarityRule is a re-factoring of the VulgarityFilter.  The class has
 * been written to be a Validator Framework rule.
 */

public class VulgarityRule {
    
    /*
       This class will take a list of comma-separated words of vulgarities.  (badWords parameter)
       It will then parse through the value string and check to see if the string
       contains any of these vulgarities.  If the String does, it will return true,
       otherwise it will return false.
    */
    private static boolean isOffensive(String badWords, String value){
      StringTokenizer tokenizer = new StringTokenizer(badWords,",");

      while (tokenizer.hasMoreTokens()){
        String badWord = tokenizer.nextToken().toLowerCase().trim();
        if (value.toLowerCase().indexOf(badWord)!=-1){
          return true;
        } 
      }
      
      return false; 
    }

    
    /**
     * This isOffensive() method acts as an entry point for the Validator framework.
     * The parameters passed in are used to provide information to the rule.
     * 
	 * @param targetObject ActionForm class that is going to be validation.
	 * @param validatorAction Contains meta-data about the Validation rule.  This
	 *                        meta-data will be based on the XML tags set in the
	 *                        validator-rules.xml and validation.xml
	 * @param field           Field that is going to be validated against.
	 * @param actionErrors    ActionErrors class that will contain any errors that are
	 * 						  found by the validation rule.
	 * @param request		  HTTPServletRequest.  Allows the validation method to 
	 * 						  query the request object for data.
	 * @return				  Returns true if the rule is not violated.  Returns false if
	 * 						  the rule is violated.
	 */
	public static boolean isOffensive(Object          targetObject      ,
                                      ValidatorAction validatorAction   ,
                                      Field           field             ,
                                      ActionErrors    actionErrors      ,
                                      HttpServletRequest request){

      /*Checking to see if the target is null*/
      boolean TARGET_OBJECT_IS_NULL = (targetObject == null);
      if (TARGET_OBJECT_IS_NULL) return true;

    
      /*Checking to see if the field is null*/
      String fieldValue = ValidatorUtil.getValueAsString(targetObject, field.getProperty());
      boolean FIELD_IS_NULL_OR_BLANK = GenericValidator.isBlankOrNull(fieldValue);
      if (FIELD_IS_NULL_OR_BLANK) return true;

      /*Getting the bad word list*/
      String vulgarities = field.getVarValue("vulgarities");

      /*Checking to see if the value is offensive*/
      boolean RULE_INVALID = isOffensive(vulgarities, fieldValue);

      /*Rule has been violated, add an action error*/
      if (RULE_INVALID){
        actionErrors.add(field.getKey(), 
                   Resources.getActionError(request,
                                            validatorAction,field));
        return false;
      }

      return true;
    }

}
