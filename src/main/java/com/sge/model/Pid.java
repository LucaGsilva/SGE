
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É O RESPONSÁVEL POR CRIAR UM ARQUIVO CONTENDO O PID DO PROCESSO DA APLICAÇÃO GERADO
*  NO WINDOWS 
*  
*  CAMINHO: C:\SGE\bin\pid.cvn
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



package com.sge.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Pid {

	public void CriaArquivo() {

		try {

			String[] processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@");

			FileWriter arq = new FileWriter("C:\\SGE\\bin\\pid.cvn");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.write(processName[0]);
			gravarArq.flush();
			gravarArq.close();
		} catch (IOException e) {
		}
	}

}
