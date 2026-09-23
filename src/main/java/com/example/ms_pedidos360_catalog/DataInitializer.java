package com.example.ms_pedidos360_catalog;

import com.example.ms_pedidos360_catalog.model.Product;
import com.example.ms_pedidos360_catalog.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            Product p1 = Product.builder()
                    .name("Teclado Mecánico Pro")
                    .description("Switches mecánicos táctiles, iluminación RGB y estructura de aluminio")
                    .price(new BigDecimal("79.99"))
                    .stock(25)
                    .build();

            Product p2 = Product.builder()
                    .name("Mouse Ergonómico Inalámbrico")
                    .description("Sensor óptico de alta precisión 16000 DPI con batería de larga duración")
                    .price(new BigDecimal("49.99"))
                    .stock(18)
                    .build();

            Product p3 = Product.builder()
                    .name("Monitor Gamer 27 pulgadas")
                    .description("Resolución QHD 2560x1440, tasa de refresco de 165Hz y panel IPS")
                    .price(new BigDecimal("299.99"))
                    .stock(8) // Stock bajo para activar alerta en dashboard de Admin (< 10)
                    .build();

            Product p4 = Product.builder()
                    .name("Auriculares Bluetooth con Cancelación de Ruido")
                    .description("Sonido envolvente espacial, micrófono con cancelación activa")
                    .price(new BigDecimal("129.50"))
                    .stock(5) // Stock crítico (< 10)
                    .build();

            productRepository.saveAll(List.of(p1, p2, p3, p4));
        }
    }
}
