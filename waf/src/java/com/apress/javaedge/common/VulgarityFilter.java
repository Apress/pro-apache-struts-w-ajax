/*
 * VulgarityFilter.java
 *
 * Created on September 21, 2002, 1:47 PM
 */

package com.apress.javaedge.common;

/**
 * Checks for the presence of bad words.  The code in this class has also been refactored
 * into a custom Validatior rule called VulgarityRule.
 * 
 */
public class VulgarityFilter {
    
    private static VulgarityFilter filter = null;
    
    private static String[] badWords = {"Stupid", "Idiot", "Moron", "Dummy", "Flippin", "Ninny"};
    
    private VulgarityFilter(){
      
    }
    
    static{
      filter = new VulgarityFilter();   
    }
    
    public static VulgarityFilter getInstance(){
      return filter;   
    }
    
    public boolean isOffensive(String valueToCheck){
        String currentWord = "";
        
        for (int x=0; x<=badWords.length-1; x++){
           if (valueToCheck.toLowerCase().indexOf(badWords[x].toLowerCase())!=-1) return true;
        }
        
        return false;
    }
}
