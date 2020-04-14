/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL INICIAR A APLICAÇÃO
*  
*	----------------------------------ALTERAÇÕES-------------------------------------------------
*
*
*	DESENVOLVEDOR: XXXX
*	DATA: XX/XX/XX
*	MOTIVO: XXXXXXXXXXX
*	ALTERAÇÃO: XXXXXXXXXXXX
*
*
*------------------------------------------------------------------------------------------------ */


package com.sge;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sge.model.Pid;

@SpringBootApplication
public class SgeApplication {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR"));
		SpringApplication.run(SgeApplication.class, args);
		Pid pid = new Pid();
		pid.CriaArquivo();
	}

}
