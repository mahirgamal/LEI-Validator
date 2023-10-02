package leisa.validator.restfulservice.infrastructure;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

@RestController
public class ValidatorController {
	private String errorMessage;
	private String schema;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private JsonSchema jschema;

	@PostConstruct
	public void init() {
		try (InputStream in = getClass().getResourceAsStream("LEI/eventCore.json");
		     BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append(System.lineSeparator());
			}
			schema = sb.toString();
			JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
			jschema = schemaFactory.getSchema(schema);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/valid")
	public ResponseEntity<String> validate(@RequestBody String jdata) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<String> returnValue;

		try {
			JsonNode json = objectMapper.readTree(jdata);
			Set<ValidationMessage> validationResult = jschema.validate(json);

			StringBuilder errorMessageBuilder = new StringBuilder();
			if (validationResult.isEmpty()) {
				errorMessageBuilder.append("valid");
				returnValue = ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(errorMessageBuilder.toString());
			} else {
				validationResult.forEach(vm -> errorMessageBuilder.append(vm.getMessage()).append("\r"));
				returnValue = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).contentType(MediaType.APPLICATION_JSON).body(errorMessageBuilder.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getClass().getName();
			returnValue = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).contentType(MediaType.APPLICATION_JSON).body(errorMessage);
		}

		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("Execution time in milliseconds: " + executionTime);

		return returnValue;
	}
}
