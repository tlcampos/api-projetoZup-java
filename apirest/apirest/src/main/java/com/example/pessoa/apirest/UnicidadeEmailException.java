package com.example.pessoa.apirest;

import com.example.pessoa.apirest.util.Constantes;

public class UnicidadeEmailException extends Exception {
	public UnicidadeEmailException() {
        super(Constantes.erroEmailJaCadastrado);
    }

}
