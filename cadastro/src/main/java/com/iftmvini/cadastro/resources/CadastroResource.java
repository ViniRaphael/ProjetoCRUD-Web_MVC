package com.iftmvini.cadastro.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.iftmvini.cadastro.resources.dto.CadastroDTO;

@Controller

public class CadastroResource {

    private List<CadastroDTO> cadastros = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("cadastros", cadastros);
        return "lista";
    }

    @GetMapping("/cadastroPost")
    public String formCadastro(Model model) {
        model.addAttribute("cadastro", new CadastroDTO());
        return "cadastro";
    }

    @PostMapping("/cadastroPost")
    public String doPost(CadastroDTO dto) {
        dto.setId(idCounter++);
        cadastros.add(dto);
        return "redirect:/lista";
    }

    @GetMapping("/editar/{id}")
    public String formEdicao(@PathVariable Long id, Model model) {
        CadastroDTO cadastroParaEditar = cadastros.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (cadastroParaEditar == null) {
            return "redirect:/lista";
        }

        model.addAttribute("cadastroPost", cadastroParaEditar);
        return "editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, CadastroDTO dto) {
        for (int i = 0; i < cadastros.size(); i++) {
            if (cadastros.get(i).getId().equals(id)) {
                dto.setId(id);
                cadastros.set(i, dto);
                return "redirect:/lista";
            }
        }
        return "redirect:/lista";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        cadastros.removeIf(c -> c.getId().equals(id));
        return "redirect:/lista";
    }
}
