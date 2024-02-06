package com.corporatedoc.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentType {
    STATEMENT("Заявление"),
    ORDER("Приказ"),
    PROTOCOL("Протокол"),
    ACT("Акт"),
    EXPLANATORY("Объяснительная"),
    MEMO("Докладная"),
    CONTRACT("Договор"),
    ;

    private final String name;
}

