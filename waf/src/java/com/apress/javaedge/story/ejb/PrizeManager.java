package com.apress.javaedge.story.ejb;

import com.apress.javaedge.member.MemberVO;

/**
 * @author jcarnell
 *
 * Dummy class that does not any work.  It is only here as an example class.
 */
public class PrizeManager {
    public void notifyMarketing(MemberVO memberVO, int totalCount) {
        System.out.println("The prize manager has been invoked for member: " +
                memberVO.getFirstName() + " " + memberVO.getLastName() + " for a count of " + totalCount + " submissions.");
    }

    public int checkStoryCount(MemberVO memberVO) {
        return 0;
    }

}
