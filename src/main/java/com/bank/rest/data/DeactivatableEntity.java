package com.bank.rest.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class DeactivatableEntity<T extends Serializable> extends AbstractEntity<T> {

    private static final long serialVersionUID = -7192085251437551090L;

    @Getter
    @Setter
    @Column(name = "isactive")
    private Integer isActive = 1;

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return getIsActive() > 0;
    }
}

