package photon.controller.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import photon.controller.modelo.Distribuidor;
import photon.controller.modelo.ErroHttp;
import photon.controller.repository.DistribuidorRepository;

@RestController
@RequestMapping("/distribuidor")
public class DistribuidorRestController {

	@Autowired
	private DistribuidorRepository repository;

	// insere um distribuidor
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> novoDistribuidor(
			@RequestBody Distribuidor distribuidor) {
		try {
			repository.inserir(distribuidor);
			return ResponseEntity.created(
					URI.create("/distribuidor/" + distribuidor.getId())).body(
					distribuidor);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista todos os distribuidores
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> listar() {
		try {
			return new ResponseEntity<Object>(repository.listar(),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// altera um distribuidor pelo o id dele
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> alterar(@PathVariable("id") Long id,
			@RequestBody Distribuidor distribuidor) {
		try {
			distribuidor.setId(id);
			repository.alterar(distribuidor);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(URI.create("/distribuidor/"
					+ distribuidor.getId()));
			return new ResponseEntity<>(distribuidor, header, HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista um distribuidor pelo id dele
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> buscarDistribuidor(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Object>(repository.buscar(id),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista um distribuidor pelo nome dele
	@RequestMapping(value = "/pesquisar/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> buscarDistribuidorNome(
			@PathVariable("nome") String nome) {
		try {
			return new ResponseEntity<Object>(repository.buscarPorNome(nome),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// deleta um distribuidor
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletarDistribuidor(
			@PathVariable("id") Long id) {
		try {
			repository.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	@RequestMapping(value = "/status/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> atualizar(@PathVariable("id") Long id) {
		try {
			repository.StatusD(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

}
