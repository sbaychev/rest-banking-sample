package com.bank.rest.web.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SearchError {

    /**
     * The Code.
     */
    String code;

    /**
     * The Message.
     */
    String message;

}
