package com.example.pessoa.apirest;

import com.example.pessoa.apirest.util.Constantes;

public class InvalidoCPFException extends Exception {
	public InvalidoCPFException() {
		super(Constantes.erroCPFInvalido);
	}
}

