package com.java.main.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import com.java.main.logic.Message;

@Data
@Builder
public class Response<B> {
	private B body;
	private boolean success;
	private List<Message> messages;
}
