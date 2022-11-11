package photon.controller.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import photon.controller.modelo.ErroHttp;
import photon.controller.modelo.Movimentacao;
import photon.controller.repository.MovimentacaoRepository;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoRestController {

	@Autowired
	private MovimentacaoRepository repository;

	// Guarda os rfids que estão passando em uma lista
	@RequestMapping(value = "/guarda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> guarda(@RequestBody String rfid) {
		try {
			return new ResponseEntity<Object>(repository.guarda(rfid),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// salva uma movimentação no banco de dados
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> novoProduto(@RequestBody Movimentacao mov) {
		try {
			repository.inserir(mov);
			return ResponseEntity.created(
					URI.create("/movimentacao/" + mov.getId())).body(mov);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista todos os rfids que contém na lista
	@RequestMapping(value = "/listaguarda", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> listaGuarda() {
		try {
			return new ResponseEntity<Object>(repository.listarMovi(),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// Verifica se há um rfid existente
	@RequestMapping(value = "/verificar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> Verificar() {
		try {
			return new ResponseEntity<Object>(repository.verificar(),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista todas as movimentações
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

	// deleta uma movimentação em expecifico passando o id dela
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> excluir(@PathVariable("id") Long id) {
		try {
			repository.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// mostra a quantidade no estoque do produto
	@RequestMapping(value = "estoque/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> estoque(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Object>(repository.estoque(id),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// mostra a quantidade no estoque do produto
	@RequestMapping(value = "/quantidade", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> quantidade() {
		try {
			return new ResponseEntity<Object>(repository.quantidade(),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// exibe a quantidade do produto que já saiu
	@RequestMapping(value = "estoqueSaiu/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> estoqueSaiu(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Object>(repository.estoque_saiu(id),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// altera uma movimentação
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> atualizar(
			@RequestBody Movimentacao movimentacao) {
		try {
			repository.saida(movimentacao);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista uma movimentação expecifica passando o id dela
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> buscarMovimentacao(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Object>(repository.buscar(id),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// lista todos os lotes existentes
	@RequestMapping(value = "listaLote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> listarLote() {
		try {
			return new ResponseEntity<Object>(repository.listarLote(),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// mostra a quantidade no estoque por lote
	@RequestMapping(value = "lote/{lote}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> lote(@PathVariable("lote") Long lote) {
		try {
			return new ResponseEntity<Object>(repository.lote(lote),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// exibe a quantidade no lote que já saiu
	@RequestMapping(value = "loteSaiu/{lote}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> loteSaiu(@PathVariable("lote") Long lote) {
		try {
			return new ResponseEntity<Object>(repository.lote_saiu(lote),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}

	// mostra o nome do produto do lote
	@RequestMapping(value = "loteNome/{lote}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> lote_nome(@PathVariable("lote") Long lote) {
		try {
			return new ResponseEntity<Object>(repository.nome_produto(lote),
					HttpStatus.OK);
		} catch (Exception e) {
			ErroHttp erro = new ErroHttp(HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
			return ResponseEntity.status(erro.getStatusCode()).body(erro);
		}
	}
}
