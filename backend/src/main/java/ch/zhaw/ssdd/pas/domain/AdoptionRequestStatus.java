package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.ValueObject;

@ValueObject
public enum AdoptionRequestStatus {
    SUBMITTED, IN_REVIEW, APPROVED, REJECTED;
}
