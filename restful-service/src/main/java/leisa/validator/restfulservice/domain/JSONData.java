package leisa.validator.restfulservice.domain;

import java.io.Serializable;

public class JSONData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jsondata;

	public JSONData(String jsondata) {
		this.jsondata=jsondata;
	}

	public String getJsondata() {
		return jsondata;
	}
}
