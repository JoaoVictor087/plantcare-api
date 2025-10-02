package com.plantcare.plantcare_api.DTOs;

public record PlantaDTO(
  String nomePlanta,
  String tipoPlanta
  //long idUsuario #ID do usuário vai no pathvariable da url
  //long id_planta id da planta vem do pathvariable tbm
){}
