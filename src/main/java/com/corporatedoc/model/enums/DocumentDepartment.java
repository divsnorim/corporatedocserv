package com.corporatedoc.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentDepartment {
    ACCOUNTING("Бухгалтерия"),
    HR("Центр кадровой работы"),
    LEGAL("Юридический отдел"),
    FUNCTIONAL("Функциональный отдел"),
    LABOR_PROTECTION("Отдел охраны труда"),
    ;
    
    private final String name;
}

