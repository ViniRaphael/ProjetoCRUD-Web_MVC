package com.iftmvini.cadastro.resources.dto;

import java.util.Objects;

import lombok.Data;

@Data
public class CadastroDTO {
    
    private String name;
    private String email;
    private String senha;
    private String numero;
    private Long id;

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CadastroDTO that = (CadastroDTO) o;
    return Objects.equals(id, that.id);
}

@Override
public int hashCode() {
    return Objects.hash(id);
}
}
