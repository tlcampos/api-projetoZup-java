package com.example.pessoa.apirest;

import com.example.pessoa.apirest.util.Constantes;

public class UnicidadeCPFException extends Exception {
	public UnicidadeCPFException() {
        super(Constantes.erroCPFJaCadastrado);
    }
}
