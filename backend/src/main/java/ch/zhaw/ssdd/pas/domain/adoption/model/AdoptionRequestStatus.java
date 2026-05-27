package ch.zhaw.ssdd.pas.domain.adoption.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

@ValueObject
public enum AdoptionRequestStatus {
    SUBMITTED, IN_REVIEW, APPROVED, REJECTED;
}
