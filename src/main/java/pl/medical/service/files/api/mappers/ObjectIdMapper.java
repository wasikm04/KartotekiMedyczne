package pl.medical.service.files.api.mappers;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
abstract class ObjectIdMapper {

    String mapObjectIdToString(ObjectId id) {
        return id != null ? id.toString() : null;
    }

    ObjectId mapStringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }
}
