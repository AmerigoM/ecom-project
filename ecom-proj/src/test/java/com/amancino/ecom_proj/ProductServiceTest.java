package com.amancino.ecom_proj.service;

import com.amancino.ecom_proj.exception.ProductNotFoundException;
import com.amancino.ecom_proj.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

// Unit test: no Spring, no database, no HTTP. Only ProductService runs;
// its repository is a fake object created by Mockito.
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void getProductById_throwsWhenProductDoesNotExist() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> service.getProductById(99));
    }
}

