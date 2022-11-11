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

import photon.controller.modelo.ErroHttp;
import photon.controller.modelo.Produto;
import photon.controller.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutosRestController {

	@Autowired
	private ProdutoRepository repository;

	// insere um produto
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> novoProduto(@RequestBody Produto produtos) {
		try {
			repository.inserir(produtos);
			return ResponseEntity.created(
					URI.create("/produto/" + produtos.getId())).body(produtos);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista todos os produtos
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

	// altera um produto
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> alterar(@PathVariable("id") Long id,
			@RequestBody Produto produto) {
		try {
			produto.setId(id);
			repository.alterar(produto);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(URI.create("/produto/" + produto.getId()));
			return new ResponseEntity<>(produto, header, HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista um produto pelo nome dele
	@RequestMapping(value = "/pesquisar/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> buscarProdutoNome(
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

	// lista um produto pelo id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> buscarProduto(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Object>(repository.buscar(id),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// deleta um produto
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletarProduto(@PathVariable("id") Long id) {
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
			repository.Status(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

}
