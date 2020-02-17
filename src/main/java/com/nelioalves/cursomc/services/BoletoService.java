package com.nelioalves.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import com.nelioalves.cursomc.domain.PagamentoComBoleto;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {

	// numa aplicação real, o conteúdo deste método pode ser trocado pelo de um WebService de Boleto
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instantePedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
