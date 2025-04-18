package org.gobeshona.NativeSqlDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ObjectMetaData {
    private String fieldName;
    private String dataType;
    private String getterMethodName;
}
