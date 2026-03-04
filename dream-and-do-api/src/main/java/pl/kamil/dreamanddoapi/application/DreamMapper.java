package pl.kamil.dreamanddoapi.application;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DreamMapper {
    DreamDTO toDreamDTO(Dream dream);
    Dream toDream(DreamDTO dreamDTO);
}
