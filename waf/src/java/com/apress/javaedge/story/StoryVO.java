    package com.apress.javaedge.story;

    import com.apress.javaedge.common.ValueObject;
    import com.apress.javaedge.member.MemberVO;

    import java.util.Vector;

    /**
     * Holds Story data retrieved from the JavaEdge database.
     *
     */
    public class StoryVO extends ValueObject {

        private Long storyId;
        private String storyTitle;
        private String storyIntro;
        private byte[] storyBody;
        private java.sql.Date submissionDate;
        private Long memberId;
        private MemberVO storyAuthor;
        public Vector comments = new Vector(); // of type StoryCommentVO


        public Long getStoryId() {
            return storyId;
        }

        public void setStoryId(Long storyId) {
            this.storyId = storyId;
        }

        public String getStoryTitle() {
            return storyTitle;
        }

        public void setStoryTitle(String storyTitle) {
            this.storyTitle = storyTitle;
        }

        public String getStoryIntro() {
            return storyIntro;
        }

        public void setStoryIntro(String storyIntro) {
            this.storyIntro = storyIntro;
        }

        public String getStoryBody() {
            return new String(storyBody);
        }

        public void setStoryBody(String storyBody) {
            this.storyBody = storyBody.getBytes();
        }


        public java.sql.Date getSubmissionDate() {
            return submissionDate;
        }

        public void setSubmissionDate(java.sql.Date submissionDate) {
            this.submissionDate = submissionDate;
        }


        public Vector getComments() {
            return comments;
        }

        public void setComments(Vector comments) {
            this.comments = comments;
        }

        public MemberVO getStoryAuthor() {
            return storyAuthor;
        }

        /** Setter for property storyAuthor.
         * @param storyAuthor New value of property storyAuthor.
         */
        public void setStoryAuthor(MemberVO storyAuthor) {
            this.storyAuthor = storyAuthor;
        }

    } // end StoryVO



