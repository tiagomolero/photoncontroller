package photon.controller.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import photon.controller.modelo.Produto;
import photon.controller.modelo.Status;

@Repository
public class ProdutoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public String inserir(Produto produto) {

		Query query = manager.createNativeQuery("SELECT nome from produto where nome = ?");

		query.setParameter(1, produto.getNome());

		System.out.println(query.getResultList());
		if (query.getResultList().isEmpty()) {
			produto.setStatus(Status.ATIVO);
			this.manager.persist(produto);
			return "nao tem";
		} else {
			return "tem";
		}

	}


	@Transactional
	public void alterar(Produto produto) {
		Query query = manager
				.createNativeQuery("update produto set nome = ? where id = ?");
		query.setParameter(1, produto.getNome());
		query.setParameter(2, produto.getId());
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> buscarPorNome(String nome) {
		Query query = manager
				.createNativeQuery("SELECT * FROM produto WHERE produto.nome LIKE ?");
		query.setParameter(1, "%" + nome + "%");
		System.out.println(query);
		return query.getResultList();
	}

	public Produto buscar(Long id) {
		return this.manager.find(Produto.class, id);
	}

	@Transactional
	public void excluir(Long id) {
		this.manager.remove(this.manager.find(Produto.class, id));
	}

	public List<Produto> listar() {
		TypedQuery<Produto> query = this.manager.createQuery(
				"select p from Produto p", Produto.class);
		return query.getResultList();
	}

	@Transactional
	public void Status(Long id) {
		Query query = null;
		Query query2 = null;
		query = manager
				.createNativeQuery("SELECT status from produto where id = ?");
		query.setParameter(1, id);
		query.getSingleResult();
		if (query.getSingleResult().equals(1)) {
			query2 = manager
					.createNativeQuery("update produto set status = 0 where id = ?");
			query2.setParameter(1, id);
		} else {
			query2 = manager
					.createNativeQuery("update produto set status = 1 where id = ?");
			query2.setParameter(1, id);
		}

		query2.executeUpdate();
	}
}
