package com.sample.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommentRequest {
    private String title;
    private String body;
    private int userId;
    private int postId;

    @JsonCreator
    public CommentRequest(
            @JsonProperty("title") String title,
            @JsonProperty("body") String body,
            @JsonProperty("userId") int userId,
    	@JsonProperty("postId") int postId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.postId = postId;
    }

    

    public int getPostId() {
		return postId;
	}



	public void setPostId(int postId) {
		this.postId = postId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String toJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}

