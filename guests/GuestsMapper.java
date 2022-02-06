package com.tenniscourts.guests;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestsMapper {

    GuestsDTO map(Guest source);

    Guest map(GuestsDTO source);

    List<GuestsDTO> map(List<Guest> source);
}
