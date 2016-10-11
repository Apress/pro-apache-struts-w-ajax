package com.apress.javaedge.story;

import com.apress.javaedge.common.ValueObject;
import com.apress.javaedge.member.MemberVO;

import java.sql.Date;

/**
 * Wrapper around data retrieved from the story comment table.
 *
 */
public class StoryCommentVO extends ValueObject {

    private Long commentId;
    private Long storyId;
    private Long memberId;
    private MemberVO commentAuthor;
    private Date submissionDate;
    private byte[] commentBody;



    ///////////////////////////////////////
    // access methods for attributes

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public java.sql.Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(java.sql.Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getCommentBody() {

        if (commentBody == null) return "";

        return new String(commentBody);
    }

    public void setCommentBody(String commentBody) {

        if (commentBody == null) commentBody = "";

        this.commentBody = commentBody.getBytes();
    }


    /**
     * Returns the commentAuthor.
     * @return MemberVO
     */
    public MemberVO getCommentAuthor() {
        return commentAuthor;
    }

    /**
     * Sets the commentAuthor.
     * @param commentAuthor The commentAuthor to set
     */
    public void setCommentAuthor(MemberVO commentAuthor) {
        this.commentAuthor = commentAuthor;
    }


} // end StoryCommentVO



