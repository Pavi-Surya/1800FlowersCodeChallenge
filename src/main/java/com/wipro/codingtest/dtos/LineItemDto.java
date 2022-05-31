package com.wipro.codingtest.dtos;

import java.util.Objects;

public class LineItemDto {

	private Integer id;
	
    private Integer userId;
    
    private String title;
    
    private String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(body, id, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItemDto other = (LineItemDto) obj;
		return Objects.equals(body, other.body) && Objects.equals(id, other.id) && Objects.equals(title, other.title)
				&& Objects.equals(userId, other.userId);
	}
}
