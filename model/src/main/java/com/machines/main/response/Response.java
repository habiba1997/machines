package com.machines.main.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.machines.main.logic.Message;
import com.machines.main.logic.MessageType;
import com.machines.main.logic.Validation;

@Data
@Builder
@AllArgsConstructor
public class Response<B> {
	private B body;
	private boolean success = true;
	private List<Message> messages;

	Response() {
		this.success = true;
	}

	public Response<B> fromValidation(final Validation<B> validation) {
		this.success = validation.isSuccess();
		this.messages = validation.getMessages();
		this.body = validation.getBody();
		return this;
	}

	public void setMessages(final List<Message> inMessages) {
		this.messages = inMessages;
		Boolean isAnyErrorMessage = messages.stream().anyMatch(m -> m.getMessageType().equals(MessageType.ERROR));
		if (isAnyErrorMessage) {
			this.success = false;
		}

	}
}
