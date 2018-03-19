package br.com.builders.treinamento.rest.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import br.com.builders.treinamento.domain.Sample;
import br.com.builders.treinamento.dto.response.SampleResponse;

@Service
@Lazy
public class SampleLab {

	private static final String HEADERPARAM_CHARSET = "UTF-8";

	@Value("${id}")
	private String id;


	@Autowired
	private RestTemplate restTemplate;

	/*
	public ValidaSenhaCartaoResponse validarSenha(final Cartao cartao) {
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("id", cartao.getIdCartao());
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(server + "/api/cartoes/{id}/validar-senha");
		HttpHeaders headers = createHttpHeaders();
		headers.add("senha", cartao.getSenha());

		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		ResponseEntity<String> retorno = restTemplate.exchange(builder.buildAndExpand(uriParams).encode().toUri(),
				HttpMethod.GET, httpEntity, String.class);

		Gson gson = new Gson();
		ValidaSenhaCartaoResponse response = null;
		response = gson.fromJson(retorno.getBody(), new TypeToken<ValidaSenhaCartaoResponse>() {
		}.getType());
		return response;
	}
	*/

	private HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("charset", HEADERPARAM_CHARSET);
		headers.add("id", id);
		return headers;
	}
}
