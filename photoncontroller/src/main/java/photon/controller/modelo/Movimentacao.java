package photon.controller.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String rfid;

	private int lote;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss zzz", timezone = "Brazil/East")
	private Calendar dataEntrada;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss zzz", timezone = "Brazil/East")
	private Calendar dataSaida;

	private TipoMovimento tipo_movimento;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "distribuidor", referencedColumnName = "id")
	private Distribuidor distribuidor;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "produto", referencedColumnName = "id")
	private Produto produto;

	private BigDecimal precoVenda;
	private BigDecimal precoCompra;

	public Calendar getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Calendar dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public TipoMovimento getTipo_movimento() {
		return tipo_movimento;
	}

	public void setTipo_movimento(TipoMovimento tipo_movimento) {
		this.tipo_movimento = tipo_movimento;
	}

	public Distribuidor getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(BigDecimal precoCompra) {
		this.precoCompra = precoCompra;
	}

	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", rfid=" + rfid + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", tipo_movimento=" + tipo_movimento + ", distribuidor=" + distribuidor + ", produto=" + produto
				+ ", precoVenda=" + precoVenda + ", precoCompra=" + precoCompra + "]";
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	
}
