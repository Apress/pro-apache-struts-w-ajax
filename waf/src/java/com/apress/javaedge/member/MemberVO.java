package com.apress.javaedge.member;

import com.apress.javaedge.common.ValueObject;

import java.util.HashMap;

/**
 * Wrapper around data retrieved from the member table.
 *
 * @author John Carnell
 */
public class MemberVO extends ValueObject implements java.io.Serializable{

    private Long memberId;
    private String firstName;
    private String lastName;
    private String userId;
    private String password;
    private String email;
    private HashMap addresses;


    public MemberVO(){}

    /**
     * @param email
     * @param firstName
     * @param lastName
     * @param memberId
     * @param password
     * @param stories
     * @param userId
     * @param addresses*/
    public MemberVO(String email,
    String firstName,
    String lastName,
    Long memberId,
    String password,
    String   userId,
    HashMap  addresses){
        this.email = email;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.memberId  = memberId;
        this.password  = password;
        this.userId    = userId;
        this.addresses = addresses;

    }

    ///////////////////////////////////////
    // access methods for attributes

    /**
     * @return  */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName  */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return  */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName  */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return  */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId  */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return  */
    public String getPassword() {
        return password;
    }
    /**
     * @param password  */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return  */
    public String getEmail() {
        return email;
    }
    /**
     * @param email  */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return  */
    public Long getMemberId() {
        return memberId;
    }
    /**
     * @param memberId  */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setAddress(String addressType, Object address){
      addresses.put(addressType, address);
    }

    public Object getAddress(String addressType){

        Object holder = addresses.get(addressType);

        if (holder==null) return "";

        return holder;
    }
} // end MemberVO



