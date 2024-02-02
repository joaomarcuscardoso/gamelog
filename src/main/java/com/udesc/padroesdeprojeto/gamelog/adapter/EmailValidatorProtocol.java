package com.udesc.padroesdeprojeto.gamelog.adapter;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidatorProtocol implements IEmailValidatorAdapter {

    private final EmailValidator emailValidator;

    public EmailValidatorProtocol() {
        this.emailValidator = EmailValidator.getInstance();
    }

    @Override
    public boolean isValidEmail(String email) {
        return emailValidator.isValid(email);
    }
}