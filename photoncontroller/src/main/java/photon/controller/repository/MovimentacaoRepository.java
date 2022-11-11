package photon.controller.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import photon.controller.modelo.Movimentacao;

@Repository
public class MovimentacaoRepository {

	@PersistenceContext
	private EntityManager manager;
	List<String> rfids = new ArrayList<String>();
	List<String> rfids2 = new ArrayList<String>();

	public List<String> guarda(String rfid) {
		System.out.println(rfid);
		JSONObject json = new JSONObject(rfid);

		if (rfids.contains((json.getString("rfid")))) {
			System.out.println("Ja tem!");
		} else {

			rfids.add(json.getString("rfid"));
		}

		return rfids;
	}

	public List<String> listarMovi() {
		return rfids;

	}

	/*
	 * public Object cadastrarRfid(Movimentacao mov) { Query query = null;
	 * for(int i = 1; i < rfids.size(); i++) { query =
	 * this.manager.createNativeQuery(
	 * "insert into movimentacao (rfid, precoCompra, produto, lote) value (?,?,?,?)"
	 * ,Movimentacao.class);
	 * 
	 * query.setParameter(1, rfids.get(i)); query.setParameter(2,
	 * mov.getPrecoCompra()); query.setParameter(3, mov.getProduto().getId());
	 * query.setParameter(4, mov.getLote()); query.executeUpdate();
	 * 
	 * System.out.println(rfids.get(i)); } return query; }
	 */

	@Transactional
	public void inserir(Movimentacao mov) {

		System.out.println(rfids.size());
		for (int i = 0; i < rfids.size(); i++) {
			/*
			 * Movimentacao m = new Movimentacao();
			 * m.setPrecoCompra(mov.getPrecoCompra()); m.setLote(mov.getLote());
			 * m.setProduto(mov.getProduto()); m.setRfid(rfids.get(i));
			 * m.setTipo_movimento(TipoMovimento.ENTRADA);
			 * this.manager.persist(m);
			 */

			Query query = manager
					.createNativeQuery("insert into movimentacao (rfid, produto, lote, tipo_movimento, precoCompra, dataEntrada) SELECT ?, ?, ?, 0, ?, now() FROM dual WHERE NOT exists (SELECT rfid from movimentacao WHERE rfid = ?)");
			query.setParameter(1, rfids.get(i));
			query.setParameter(2, mov.getProduto().getId());
			query.setParameter(3, mov.getLote());
			query.setParameter(4, mov.getPrecoCompra());
			query.setParameter(5, rfids.get(i));
			query.executeUpdate();
			System.out.println("ali");

		}
		rfids.removeAll(rfids);

	}

	public Movimentacao buscar(Long id) {
		TypedQuery<Movimentacao> query = manager
				.createQuery(
						"FROM Movimentacao m LEFT JOIN FETCH m.distribuidor d LEFT JOIN FETCH m.produto p WHERE m.id = :id",
						Movimentacao.class).setParameter("id", id);
		return query.getSingleResult();
	}

	public List<Movimentacao> listar() {
		TypedQuery<Movimentacao> query = manager
				.createQuery(
						"FROM Movimentacao m LEFT JOIN FETCH m.distribuidor d LEFT JOIN FETCH m.produto p",
						Movimentacao.class);
		return query.getResultList();
	}

	@Transactional
	public void excluir(Long id) {
		manager.remove(buscar(id));
	}

	public Object estoque(Long id) {
		Query query = manager
				.createNativeQuery("SELECT COUNT(rfid) FROM movimentacao where produto = ? && tipo_movimento = 0");
		query.setParameter(1, id);
		return query.getSingleResult();
	}

	public Object estoque_saiu(Long id) {
		Query query = manager
				.createNativeQuery("SELECT COUNT(rfid) FROM movimentacao where produto = ? && tipo_movimento = 1");
		query.setParameter(1, id);
		return query.getSingleResult();
	}

	public Object quantidade() {
		Query query = manager
				.createNativeQuery("SELECT COUNT(rfid) FROM movimentacao");
		return query.getSingleResult();
	}

	@Transactional
	// ao inves de receber movimentacao, recebe somente o rfid
	public void saida(Movimentacao movimentacao) {

		for (int i = 0; i < rfids.size(); i++) {
			Query query = manager
					.createNativeQuery("update movimentacao set dataSaida = now(), tipo_movimento = 1 , distribuidor = ?, precoVenda = ? where rfid = ?");
			query.setParameter(1, movimentacao.getDistribuidor().getId());
			query.setParameter(2, movimentacao.getPrecoVenda());
			query.setParameter(3, rfids.get(i));
			query.executeUpdate();
		}
		rfids.removeAll(rfids);
		
	}

	public List<Movimentacao> listarLote() {
		Query query = manager
				.createNativeQuery("select distinct m.lote as lote from Movimentacao m");
		return query.getResultList();
	}

	public Object lote(Long lote) {
		Query query = manager
				.createNativeQuery("SELECT COUNT(rfid) FROM movimentacao where lote = ? && tipo_movimento = 0");
		query.setParameter(1, lote);
		return query.getSingleResult();
	}

	public Object lote_saiu(Long lote) {
		Query query = manager
				.createNativeQuery("SELECT COUNT(rfid) FROM movimentacao where lote = ? && tipo_movimento = 1");
		query.setParameter(1, lote);
		return query.getSingleResult();
	}

	public Object nome_produto(Long lote) {
		Query query = manager
				.createNativeQuery("SELECT p.nome FROM movimentacao m LEFT JOIN produto p on p.id = m.produto where lote = ?");
		query.setParameter(1, lote);
		return query.getResultList();
	}

	@Transactional
	public List<String> verificar() {

		System.out.println(rfids);
		rfids2.removeAll(rfids2);
		for (int i = 0; i < rfids.size(); i++) {
			System.out.println(rfids.get(i));
			Query query = manager
					.createNativeQuery("SELECT rfid FROM movimentacao WHERE rfid = ?");
			query.setParameter(1, rfids.get(i));
			if (query.getResultList().isEmpty()) {

			} else {
				if (rfids2.contains(rfids.get(i))) {

				} else {
					rfids2.add(rfids.get(i));
				}

				// rfids.remove(rfids.get(i));
			}

		}
		System.out.println("Lista final:");
		return rfids2;
	}
}
