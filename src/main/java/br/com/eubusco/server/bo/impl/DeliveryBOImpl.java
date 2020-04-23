package br.com.eubusco.server.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.AddressBO;
import br.com.eubusco.server.bo.ContactBO;
import br.com.eubusco.server.bo.DeliveryBO;
import br.com.eubusco.server.bo.UsuarioBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.DeliveryDAO;
import br.com.eubusco.server.dto.DeliveryDTO;
import br.com.eubusco.server.dto.PaginationDTO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class DeliveryBOImpl implements DeliveryBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeliveryDAO deliveryDAO;

	@Autowired
	private UsuarioBO usuarioBO;

	@Autowired
	private AddressBO addressBO;

	@Autowired
	private ContactBO contactBO;

	@Override
	public PaginationDTO getUserDeliveries(Integer userCode, Integer page) {
		logger.info("==> Executando o método getUserDeliveries.");

		if (userCode == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		List<Entrega> deliveryList = deliveryDAO.getUserDeliveries(userCode, page);

		List<DeliveryDTO> deliveryDTOList = new ArrayList<>();

		deliveryList.forEach(x -> {
			DeliveryDTO deliveryDTO = new DeliveryDTO();

			deliveryDTO.setBulk(x.getVolume());
			deliveryDTO.setClientName(usuarioBO.buscarNomeUsuario(userCode));
			deliveryDTO.setCollectionAddress(addressBO.getAddressFromCode(x.getCodigoEnderecoColeta()));
			deliveryDTO.setDeliveryAddress(addressBO.getAddressFromCode(x.getCodigoEnderecoEntrega()));
			deliveryDTO.setCollectionDate(x.getDataColeta());
			deliveryDTO.setDeliveryCode(x.getId());
			deliveryDTO.setDeliveryDate(x.getDataPrazoEntrega());
			deliveryDTO.setDeliveryDescriptio(x.getDescricao());
			deliveryDTO.setDeliveryTitle(x.getTitulo());
			deliveryDTO.setContacts(contactBO.getFromUser(userCode));

			deliveryDTOList.add(deliveryDTO);
		});

		PaginationDTO paginationDTO = new PaginationDTO();
		paginationDTO.setList(deliveryDTOList);
		paginationDTO.setCount(deliveryDAO.getCountUserDeliveries(userCode));

		return paginationDTO;
	}

}
