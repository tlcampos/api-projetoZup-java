package com.example.pessoa.apirest.controler;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pessoa.apirest.InvalidoCPFException;
import com.example.pessoa.apirest.InvalidoDataException;
import com.example.pessoa.apirest.InvalidoEmailException;
import com.example.pessoa.apirest.UnicidadeCPFException;
import com.example.pessoa.apirest.UnicidadeEmailException;
import com.example.pessoa.apirest.models.Pessoa;
import com.example.pessoa.apirest.repository.PessoaRepository;
import com.example.pessoa.apirest.util.UtilCommon;

@RestController
@RequestMapping(value="/api")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@PostMapping("/pessoa")
	public ResponseEntity<Pessoa> salvaPessoa(@RequestBody Pessoa pessoa) throws Exception{
		verificaDados(pessoa);

		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		return new ResponseEntity<>(pessoaSalva, HttpStatus.CREATED);
	}

	public void verificaDados(Pessoa pessoa) throws UnicidadeCPFException, UnicidadeEmailException, InvalidoDataException, InvalidoCPFException, InvalidoEmailException{
		Optional<Pessoa> pessoaCPF = pessoaRepository.findByCPF(pessoa.getCPF());

		if(!UtilCommon.cpfValido(pessoa.getCPF())){
			throw new InvalidoCPFException();

		}

		if(!UtilCommon.emailValido(pessoa.getEmail())) {
			throw new InvalidoEmailException();

		}
		
		if(UtilCommon.dataInvalida(pessoa.getDtNascimento())){
			throw new InvalidoDataException();
		}

		if (pessoaCPF.isPresent()) {
			throw new UnicidadeCPFException();
		}

		Optional<Pessoa> pessoaEmail = pessoaRepository.findByemail(pessoa.getEmail());
		if (pessoaEmail.isPresent()) {
			throw new UnicidadeEmailException();
		}

	}
	@ExceptionHandler({InvalidoEmailException.class})
	public ResponseEntity<Erro> handleEmailInvalidaException(InvalidoEmailException e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({InvalidoCPFException.class})
	public ResponseEntity<Erro> handleCPFInvalidaException(InvalidoCPFException e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({InvalidoDataException.class})
	public ResponseEntity<Erro> handleDataInvalidaException(InvalidoDataException e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({UnicidadeCPFException.class})
	public ResponseEntity<Erro> handleUnicidadeCpfException(UnicidadeCPFException e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({UnicidadeEmailException.class})
	public ResponseEntity<Erro> handleUnicidadeEmailException(UnicidadeEmailException e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
class Erro {
	private final String erro;

	public Erro(String erro) {
		this.erro = erro;
	}

	public String getErro() {
		return erro;
	}
}
