package com.indra.apirest.service;

import com.indra.apirest.model.TipoCambio;
import com.indra.apirest.repository.TipoCambioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoCambioServicio {

    @Autowired
    private TipoCambioRepositorio repositorio;

    public Double aplicarTipoCambio(Double monto, String monedaOrigen, String monedaDestino) {
        TipoCambio tipoCambio = repositorio.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino)
                .orElseThrow(() -> new RuntimeException("No se encontró el tipo de cambio"));
        return monto * tipoCambio.getTipoCambio();
    }

    public TipoCambio actualizarTipoCambio(String monedaOrigen, String monedaDestino, Double nuevoTipoCambio) {
        Optional<TipoCambio> tipoCambioOpt = repositorio.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino);
        TipoCambio tipoCambio = tipoCambioOpt.orElse(new TipoCambio());
        tipoCambio.setMonedaOrigen(monedaOrigen);
        tipoCambio.setMonedaDestino(monedaDestino);
        tipoCambio.setTipoCambio(nuevoTipoCambio);
        return (TipoCambio) repositorio.save(tipoCambio);
    }
}
