package igrek.webdict.controller.payload;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class PayloadResponse<T> {
	
	private int httpStatus;
	private String message;
	private T payload;
	
	private PayloadResponse(HttpStatus httpStatus, String message, T payload) {
		this.httpStatus = httpStatus.value();
		this.message = message;
		this.payload = payload;
	}
	
	public static <U> PayloadResponse<U> ok(U payload, String successMessage) {
		return new PayloadResponse<>(HttpStatus.OK, successMessage, payload);
	}
	
	public static <U> PayloadResponse<U> ok(U payload) {
		return new PayloadResponse<>(HttpStatus.OK, null, payload);
	}
	
	public static <U> PayloadResponse<U> ok(String successMessage) {
		return new PayloadResponse<>(HttpStatus.OK, successMessage, null);
	}
	
	public static <U> PayloadResponse<U> ok() {
		return new PayloadResponse<>(HttpStatus.OK, null, null);
	}
	
	public static <U> PayloadResponse<U> error(HttpStatus httpStatus, String errorMessage) {
		return new PayloadResponse<>(httpStatus, errorMessage, null);
	}
	
	public static <U> PayloadResponse<U> error(String errorMessage) {
		return new PayloadResponse<>(HttpStatus.BAD_REQUEST, errorMessage, null);
	}
	
}
