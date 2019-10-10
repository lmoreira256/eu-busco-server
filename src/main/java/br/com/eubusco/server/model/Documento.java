package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_documento")
@SequenceGenerator(name = "gen_documento", sequenceName = "gen_documento", allocationSize = 1)
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_documento")
	@GeneratedValue(generator = "gen_documento", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_tipodocumento")
	private Integer codigoTipoDocumento;

	@Column(name = "cd_usuario")
	private Integer codigoUsuario;

	@Column(name = "nr_documento")
	private Long documento;

	public Documento() {
	}

	public Documento(Integer codigoTipoDocumento, Integer codigoUsuario, Long documento) {
		super();
		this.codigoTipoDocumento = codigoTipoDocumento;
		this.codigoUsuario = codigoUsuario;
		this.documento = documento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

}
