package com.example.pessoa.apirest;

import com.example.pessoa.apirest.util.Constantes;

public class InvalidoDataException extends Exception {
	public InvalidoDataException() {
		super(Constantes.erroDataInvalida);
	}
}
