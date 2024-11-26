package com.indra.apirest.service;

import com.indra.apirest.exception.TipoCambioNoEncontradoException;
import com.indra.apirest.model.TipoCambio;
import com.indra.apirest.repository.TipoCambioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoCambioServicio {

    @Autowired
    private TipoCambioRepositorio repositorio;

    /*
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
    }*/

    public Double aplicarTipoCambio(Double monto, String monedaOrigen, String monedaDestino) {
        validarParametros(monto, monedaOrigen, monedaDestino);

        TipoCambio tipoCambio = obtenerTipoCambio(monedaOrigen, monedaDestino);
        return monto * tipoCambio.getTipoCambio();
    }

    public TipoCambio actualizarTipoCambio(String monedaOrigen, String monedaDestino, Double nuevoTipoCambio) {
        validarParametros(nuevoTipoCambio, monedaOrigen, monedaDestino);

        Optional<TipoCambio> tipoCambioOpt = repositorio.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino);
        TipoCambio tipoCambio = tipoCambioOpt.orElse(new TipoCambio());
        tipoCambio.setMonedaOrigen(monedaOrigen);
        tipoCambio.setMonedaDestino(monedaDestino);
        tipoCambio.setTipoCambio(nuevoTipoCambio);

        return repositorio.save(tipoCambio);
    }

    private TipoCambio obtenerTipoCambio(String monedaOrigen, String monedaDestino) {
        return repositorio.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino)
                .orElseThrow(() -> new TipoCambioNoEncontradoException(
                        "No se encontró un tipo de cambio para " + monedaOrigen + " a " + monedaDestino));
    }

    private void validarParametros(Double monto, String monedaOrigen, String monedaDestino) {
        if (monto == null || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }
        if (monedaOrigen == null || monedaOrigen.length() != 3) {
            throw new IllegalArgumentException("La moneda de origen debe ser un código ISO de 3 caracteres.");
        }
        if (monedaDestino == null || monedaDestino.length() != 3) {
            throw new IllegalArgumentException("La moneda de destino debe ser un código ISO de 3 caracteres.");
        }
    }
}
