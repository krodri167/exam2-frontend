package ec.edu.insteclrg.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;

import ec.edu.insteclrg.dto.ApiResponseDTO;
import ec.edu.insteclrg.dto.CategoriaDTO;

public class CategoriaHandler extends ApiHandler<CategoriaDTO> {

	// ******************************************************
	// TODO
	// - Agregar métodos faltantes (eliminar)
	// - Refactorizar (Endpoint se repite, entro otros)
	//
	//
	// * Endpoint = http://localhost:8080/api/v1.0/categoria/...
	// ******************************************************

	private static final long serialVersionUID = 1L;

	public ApiResponseDTO<CategoriaDTO> guardar(CategoriaDTO dto) {
		String remotePath = "http://localhost:8080/api/v1.0/categoria/guardar";
		String responseJsonStr = super.consumeREST(dto, HttpMethod.POST, remotePath);
		ErrorJsonHandler<CategoriaDTO> jsonHandler = new ErrorJsonHandler<CategoriaDTO>(dto);
		ApiResponseDTO<CategoriaDTO> response = jsonHandler.getServerResponse(responseJsonStr);
		return response;

	}

	public ApiResponseDTO<CategoriaDTO> actualizar(CategoriaDTO dto) {
		// TODO
		// Agregar código

		return null;

	}

	// TODO
	// * Ageregar método eliminar

	public ApiResponseDTO<CategoriaDTO> buscar(Long id) {
		CategoriaDTO dto = new CategoriaDTO();
		String remotePath = "http://localhost:8080/api/v1.0/categoria/" + id + "/buscar";
		String responseJsonStr = super.consumeREST(dto, HttpMethod.GET, remotePath);
		ErrorJsonHandler<CategoriaDTO> jsonHandler = new ErrorJsonHandler<CategoriaDTO>(dto);
		ApiResponseDTO<CategoriaDTO> response = jsonHandler.getServerResponse(responseJsonStr);
		return response;

	}

	public ApiResponseDTO<List<CategoriaDTO>> buscarTodo() {
		List<CategoriaDTO> dto = new ArrayList<>();
		String remotePath = "http://localhost:8080/api/v1.0/categoria/listar";
		String responseJsonStr = super.consumeREST(dto, HttpMethod.GET, remotePath);
		ErrorJsonHandler<List<CategoriaDTO>> jsonHandler = new ErrorJsonHandler<List<CategoriaDTO>>(dto);
		ApiResponseDTO<List<CategoriaDTO>> response = jsonHandler.getServerResponse(responseJsonStr);
		return response;

	}
}
