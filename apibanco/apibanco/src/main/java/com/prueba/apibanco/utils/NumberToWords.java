package com.prueba.apibanco.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberToWords {
    private static final String[] UNIDADES = {
            "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve",
            "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"
    };

    private static final String[] DECENAS = {
            "", "", "veinte", "treinta", "cuarenta", "cincuenta",
            "sesenta", "setenta", "ochenta", "noventa"
    };

    private static final String[] CENTENAS = {
            "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos",
            "seiscientos", "setecientos", "ochocientos", "novecientos"
    };

    public static String convert(BigDecimal numero) {
        int entero = numero.intValue();
        int decimales = numero.remainder(BigDecimal.ONE).movePointRight(2).intValue();

        String parteEntera;
        if (entero == 0) {
            parteEntera = "cero";
        } else if (entero == 100) {
            parteEntera = "cien";
        } else if (entero < 20) {
            parteEntera = UNIDADES[entero];
        } else if (entero < 100) {
            parteEntera = DECENAS[entero / 10] + (entero % 10 != 0 ? " y " + UNIDADES[entero % 10] : "");
        } else if (entero < 1000) {
            int centenas = entero / 100;
            int resto = entero % 100;
            parteEntera = CENTENAS[centenas];
            if (resto > 0) {
                if (resto < 20) {
                    parteEntera += " " + UNIDADES[resto];
                } else {
                    parteEntera += " " + DECENAS[resto / 10];
                    if (resto % 10 != 0) {
                        parteEntera += " y " + UNIDADES[resto % 10];
                    }
                }
            }
        } else {
            parteEntera = "número fuera de rango";
        }

        String parteDecimal = " con " + new DecimalFormat("00").format(decimales) + "/100";
        return parteEntera + parteDecimal;
    }
}

