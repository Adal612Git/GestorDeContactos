package com.gestordecontactos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio de ejemplo para probar inyección de dependencias.
 */
public class HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    public void sayHello() {
        logger.info("Hola desde HelloService");
    }
}
