package com.br.clean.arch.application.gateways.address;

import java.util.List;

import com.br.clean.arch.domain.entitie.address.Charge;

public interface RepositoryCharge {

	Charge createCharge (Charge charge);
	List<Charge> listCharge ();
}
