package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.ReferralDto;
import pl.medical.service.files.models.Referral;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface ReferralMapper {

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "userMail", ignore = true)
    ReferralDto mapToReferralDto(Referral referral);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    Referral mapToReferral(ReferralDto ref);
}
