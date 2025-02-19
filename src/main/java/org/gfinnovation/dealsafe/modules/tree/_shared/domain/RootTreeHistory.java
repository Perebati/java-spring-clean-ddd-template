package org.gfinnovation.dealsafe.modules.tree._shared.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.gfinnovation.dealsafe._shared.domain.GenericClass;
import org.gfinnovation.dealsafe.utils.annotations.Default;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class RootTreeHistory
 * @since v1.0 (19/02/2025)
 */

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RootTreeHistory extends GenericClass {
    private String Json;
    private String name;

    @Default
    public RootTreeHistory(
            String json,
            String name
    ) {
        this.Json = json;
        this.name = name;
    }
}
