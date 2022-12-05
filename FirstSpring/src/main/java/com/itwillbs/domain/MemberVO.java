package com.itwillbs.domain;

import com.google.protobuf.Timestamp;

import lombok.Data;


@Data
public class MemberVO {
	//VO(Value Object)
	
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	
	private Timestamp regdate;
	private Timestamp updatedate;
}
