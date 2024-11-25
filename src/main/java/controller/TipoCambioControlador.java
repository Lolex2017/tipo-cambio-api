package controller;


import com.indra.apirest.model.TipoCambio;
import com.indra.apirest.service.TipoCambioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tipo-cambio")
public class TipoCambioControlador {

    @Autowired
    private TipoCambioServicio servicio;

    @PostMapping("/aplicar")
    public ResponseEntity<?> aplicarTipoCambio(@RequestBody Map<String, Object> solicitud) {
        Double monto = Double.valueOf(solicitud.get("monto").toString());
        String monedaOrigen = solicitud.get("monedaOrigen").toString();
        String monedaDestino = solicitud.get("monedaDestino").toString();

        Double montoConvertido = servicio.aplicarTipoCambio(monto, monedaOrigen, monedaDestino);
        return ResponseEntity.ok(Map.of(
                "monto", monto,
                "montoConvertido", montoConvertido,
                "monedaOrigen", monedaOrigen,
                "monedaDestino", monedaDestino,
                "tipoCambio", montoConvertido / monto
        ));
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarTipoCambio(@RequestBody Map<String, Object> solicitud) {
        String monedaOrigen = solicitud.get("monedaOrigen").toString();
        String monedaDestino = solicitud.get("monedaDestino").toString();
        Double nuevoTipoCambio = Double.valueOf(solicitud.get("tipoCambio").toString());

        TipoCambio tipoCambioActualizado = servicio.actualizarTipoCambio(monedaOrigen, monedaDestino, nuevoTipoCambio);
        return ResponseEntity.ok(tipoCambioActualizado);
    }
}
