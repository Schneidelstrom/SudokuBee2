public enum PenaltyType {
    /*
     * Default method: Counts the number of duplicate values in each row and column. Sub-grid conflicts are intrinsically handled by the Bee generation algorithm.
     */
    ROW_COLUMN_CONFLICTS,

    /*
     * Calculates penalty based on the deviation from the expected sum and product for each row and column. Assumes sub-grids are intrinsically valid.
     */
    SUM_PRODUCT_CONSTRAINED,

    /*
     * Calculates penalty based on the deviation from the expected sum and product for every row, column, AND sub-grid.
     */
    SUM_PRODUCT_UNCONSTRAINED
}