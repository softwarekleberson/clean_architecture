package com.br.clean.arch.application.gateways.address;

import java.util.List;

import com.br.clean.arch.address.domain.entitie.address.Charge;

public interface RepositoryCharge {

	Charge createCharge (Charge charge);
	List<Charge> listCharge ();
}
