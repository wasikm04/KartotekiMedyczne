package pl.medical.service.files.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.medical.service.files.api.ReferralDto;
import pl.medical.service.files.models.Referral;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReferralMapper {

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    ReferralDto mapToReferralDto(Referral referral);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    Referral mapToReferral(ReferralDto ref);
}
