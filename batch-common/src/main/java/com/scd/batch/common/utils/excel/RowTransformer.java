package com.scd.batch.common.utils.excel;

@FunctionalInterface
public interface RowTransformer {

    /**
     * Transform each row
     *
     * @param cells of each row
     * @return The transformed row.
     */
    Object transformRow(Object[] cells);

}