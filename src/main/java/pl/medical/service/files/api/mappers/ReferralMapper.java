package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.medical.service.files.api.ReferralDto;
import pl.medical.service.files.models.Referral;

@Mapper(componentModel = "spring")
public interface ReferralMapper {
    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    ReferralDto mapToReferralDto(Referral referral);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    @Mapping(ignore = true, target = "userMail")
    Referral mapToReferral(ReferralDto ref);
}
