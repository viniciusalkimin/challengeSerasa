package br.com.alkimin.userapi.published;

import java.io.Serializable;

/**
 * @author Vinicius Alkimin.
 */
public class APIGenericResponse implements Serializable {

	private static final long serialVersionUID = 1503764240637501124L;
	
	private Object result;
	private StatusResponse status;

	public APIGenericResponse (Object result, StatusResponse status) {
		this.result = result;
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public StatusResponse getStatus() {
		return status;
	}

	public void setStatus(StatusResponse status) {
		this.status = status;
	}
	
}
