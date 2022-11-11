package photon.controller.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import photon.controller.modelo.Distribuidor;
import photon.controller.modelo.Status;

@Repository
public class DistribuidorRepository {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void inserir(Distribuidor distribuidor) {
		distribuidor.setStatus(Status.ATIVO);
		this.manager.persist(distribuidor);
	}

	public List<Distribuidor> buscarPorNome(String nome) {
		Query query = manager
				.createNativeQuery("SELECT * FROM distribuidor WHERE distribuidor.nome LIKE ?");
		query.setParameter(1, "%" + nome + "%");
		return query.getResultList();
	}

	@Transactional
	public void alterar(Distribuidor distribuidor) {
		Query query = manager
				.createNativeQuery("update distribuidor set nome = ? where id = ?");
		query.setParameter(1, distribuidor.getNome());
		query.setParameter(2, distribuidor.getId());
		query.executeUpdate();
	}

	public Distribuidor buscar(Long id) {
		return this.manager.find(Distribuidor.class, id);
	}

	@Transactional
	public void excluir(Long id) {
		this.manager.remove(this.manager.find(Distribuidor.class, id));
	}

	public List<Distribuidor> listar() {
		TypedQuery<Distribuidor> query = this.manager.createQuery(
				"select d from Distribuidor d", Distribuidor.class);
		return query.getResultList();
	}

	@Transactional
	public void StatusD(Long id) {
		Query query = null;
		Query query2 = null;
		query = manager
				.createNativeQuery("SELECT status from distribuidor where id = ?");
		query.setParameter(1, id);
		query.getSingleResult();
		if (query.getSingleResult().equals(1)) {
			query2 = manager
					.createNativeQuery("update distribuidor set status = 0 where id = ?");
			query2.setParameter(1, id);
		} else {
			query2 = manager
					.createNativeQuery("update distribuidor set status = 1 where id = ?");
			query2.setParameter(1, id);
		}

		query2.executeUpdate();
	}

}
