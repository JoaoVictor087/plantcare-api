package com.plantcare.plantcare_api.DTOs.response;

public record RestErrorDTO(
   int codigoErro,

   String nomeErro,

   String descricaoErro
) {}
