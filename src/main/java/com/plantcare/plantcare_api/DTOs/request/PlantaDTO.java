package com.plantcare.plantcare_api.DTOs.request;

public record PlantaDTO(
  String nome,
  String especie
  //long idUsuario #ID do usuário vai no pathvariable da url
  //long id_planta id da planta vem do pathvariable tbm
){}
