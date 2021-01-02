package com.example.pessoa.apirest;

import com.example.pessoa.apirest.util.Constantes;

public class InvalidoEmailException extends Exception{
	public InvalidoEmailException() {
		super(Constantes.erroEmailInvalido);
	}

}
