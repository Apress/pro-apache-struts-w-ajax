package com.apress.javaedge.struts.tiles;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Administrator
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TilesTestAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, 
                                 ActionForm     form,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        return (mapping.findForward("tiles.success"));
    }
}
