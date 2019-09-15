package com.toonext;

import static com.toonext.core.dao.IDAO.SCHEMA;

public interface IPlainListsObject{
    String PLAINLISTS_CODE = "pl";
    String LABELS_TABLE_SHORT_NAME = PLAINLISTS_CODE + "__labels";
    String LABELS_TABLE_FULL_NAME = SCHEMA + "."  + LABELS_TABLE_SHORT_NAME;
}
