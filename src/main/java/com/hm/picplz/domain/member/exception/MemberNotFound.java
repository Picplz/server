package com.hm.picplz.domain.member.exception;

import com.hm.picplz.global.error.BaseErrorException;

public class MemberNotFound extends BaseErrorException {

    public static final MemberNotFound EXCEPTION = new MemberNotFound();

    private MemberNotFound() {
        super(MemberErrorCode.MEMBER_NOT_FOUND);
    }
}
