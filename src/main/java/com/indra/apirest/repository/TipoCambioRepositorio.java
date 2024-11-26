package com.indra.apirest.repository;


import com.indra.apirest.model.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCambioRepositorio extends JpaRepository<TipoCambio, Long> {
    Optional<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
