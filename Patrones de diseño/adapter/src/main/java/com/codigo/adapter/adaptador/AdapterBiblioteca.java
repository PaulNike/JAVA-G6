package com.codigo.adapter.adaptador;

import com.codigo.adapter.antiguo.BibliotecaAntigua;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class AdapterBiblioteca {
    private final BibliotecaAntigua bibliotecaAntigua =
            new BibliotecaAntigua();

    public String obtenerDetalleLibro(int idLibro){
        //Obtener la info en XML
        String detalle = bibliotecaAntigua.obtenerInfoAntigua(idLibro);
        //Traducir XML A JSON
        JSONObject jsonObject = XML.toJSONObject(detalle);
        //retornar la Rspta
        return jsonObject.toString();
    }
}
