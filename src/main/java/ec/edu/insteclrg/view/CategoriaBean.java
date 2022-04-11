package ec.edu.insteclrg.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.edu.insteclrg.common.MensajesTipo;
import ec.edu.insteclrg.dto.ApiResponseDTO;
import ec.edu.insteclrg.dto.CategoriaDTO;
import ec.edu.insteclrg.handler.CategoriaHandler;
import ec.edu.insteclrg.utils.Mensajes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("view")
public class CategoriaBean {

	// TODO
	// Completar los métodos para que cumplan su función

	private long id;

	private CategoriaDTO categoriaDTO;

	private CategoriaHandler handler;

	private List<CategoriaDTO> registros;

	private CategoriaDTO selectedProduct;

	private List<CategoriaDTO> selectedProducts;

	private String idBuscar;

	@PostConstruct
	public void init() {
		categoriaDTO = new CategoriaDTO();
		handler = new CategoriaHandler();
		cargarTodosRegistros();
	}

	private void cargarTodosRegistros() {
		ApiResponseDTO<List<CategoriaDTO>> response = handler.buscarTodo();
		ObjectMapper mapper = new ObjectMapper();
		if (response.isSuccess())
			this.registros = mapper.convertValue(response.getResult(), new TypeReference<List<CategoriaDTO>>() {
			});
	}

	public void openNew() {
		this.selectedProduct = new CategoriaDTO();
	}

	public void saveProduct() {
		if (this.selectedProduct.getId() == 0) {
			ApiResponseDTO<CategoriaDTO> response = handler.guardar(selectedProduct);
			if (response.isSuccess()) {
				cargarTodosRegistros();
				Mensajes.addMsg(MensajesTipo.INFORMACION, "Guardado correctamente");
			} else
				Mensajes.addMsg(MensajesTipo.ERROR, "Error: no se pudo guardar");
		} else {
			// TODO
			// Código para actualizar usando API REST
		}
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("frm:growl", "frm:dt-products");
	}

	public void deleteProduct() {
		// TODO
		// Codigo para eliminar usando API REST

		this.registros.remove(this.selectedProduct);
		this.selectedProduct = null;
		Mensajes.addMsg(MensajesTipo.INFORMACION, "Eliminado correctamente");
		PrimeFaces.current().ajax().update("frm:growl", "frm:dt-products");
	}

	public void deleteSelectedProducts() {
		// TODO
		// Codigo para eliminar lo seleccionado usando API REST

		this.registros.removeAll(this.selectedProducts);
		this.selectedProducts = null;
		Mensajes.addMsg(MensajesTipo.INFORMACION, "Registros eliminados correctamente");
		PrimeFaces.current().ajax().update("frm:growl", "frm:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedProducts()) {
			int size = this.selectedProducts.size();
			return size > 1 ? size + " registros seleccionados" : "1 registro seleccionado";
		}
		return "Eliminar";
	}

	public boolean hasSelectedProducts() {
		return this.selectedProducts != null && !this.selectedProducts.isEmpty();
	}

	public void buscarPorId() {
		// TODO
		// Codigo para buscar por ID usando API REST
		// Use idBuscar --> Long.parseLong(idBuscar)

		Mensajes.addMsg(MensajesTipo.INFORMACION, "TODO: SE DEBE CODIFICAR");
		PrimeFaces.current().ajax().update("frm:growl", "frm:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}
}
