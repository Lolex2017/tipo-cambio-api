package com.indra.apirest.model;

import jakarta.persistence.*;


@Entity
public class TipoCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Código ISO de la moneda de origen (ejemplo: "USD")
    private String monedaOrigen;
    // Código ISO de la moneda de destino (ejemplo: "PEN").
    private String monedaDestino;
    // Tipo de cambio entre la moneda de origen y la moneda de destino.
    private double tipoCambio;

   // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("El ID debe ser un valor positivo.");
        }
        this.id = id;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }


    // Validamos que el código de moneda de origen sea diferente del vacío como también sea de 3 dígitos
    public void setMonedaOrigen(String monedaOrigen) {
        if (monedaOrigen == null || monedaOrigen.isEmpty() || monedaOrigen.length() != 3) {
            throw new IllegalArgumentException("La moneda de origen debe ser un código ISO de 3 caracteres.");
        }
        this.monedaOrigen = monedaOrigen.toUpperCase();
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    // Validamos que el código de moneda de destino sea diferente del vacío como también sea de 3 dígitos
    public void setMonedaDestino(String monedaDestino) {
        if (monedaDestino == null || monedaDestino.isEmpty() || monedaDestino.length() != 3) {
            throw new IllegalArgumentException("La moneda de destino debe ser un código ISO de 3 caracteres.");
        }
        this.monedaDestino = monedaDestino.toUpperCase();
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    // Validamos que el tipo de  cambio sea mayor a 0
    public void setTipoCambio(double tipoCambio) {
        if (tipoCambio <= 0) {
            throw new IllegalArgumentException("El tipo de cambio debe ser mayor a 0.");
        }
        this.tipoCambio = tipoCambio;
    }

    // Validamos la depuraciòn  mostrando descripciòn de la clase
    @Override
    public String toString() {
        return String.format(
                "TipoCambio{id=%d, monedaOrigen='%s', monedaDestino='%s', tipoCambio=%.2f}",
                id, monedaOrigen, monedaDestino, tipoCambio
        );
    }

}
