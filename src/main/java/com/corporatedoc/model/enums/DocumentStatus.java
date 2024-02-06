package com.corporatedoc.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentStatus {
    SIGNED("Подписан"),
    NOT_SIGNED("Не подписан"),
    MODIFICATION("Доработка"),
    ;

    private final String name;
}

